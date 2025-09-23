package com.example.cotaodosativosdabolsa

import android.graphics.Insets.add
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.load
import coil.size.Scale
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.net.URL
import javax.net.ssl.HttpsURLConnection

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etTicker = findViewById<EditText>(R.id.etTicker)
        val tvShortName = findViewById<TextView>(R.id.tvShortName)
        val tvCurrency = findViewById<TextView>(R.id.tvCurrency)
        val tvMarketPrice = findViewById<TextView>(R.id.tvMarketPrice)
        val tvMarketPreviousClose = findViewById<TextView>(R.id.tvMarketPreviousClose)
        val tvMarketChange = findViewById<TextView>(R.id.tvMarketChange)
        val tvMarketChangePercent = findViewById<TextView>(R.id.tvMarketChangePercent)
        val tvDayRange = findViewById<TextView>(R.id.tvDayRange)
        val tvFiftyTwoWeekRange = findViewById<TextView>(R.id.tvFiftyTwoWeekRange)
        val ivLogo = findViewById<ImageView>(R.id.ivLogo)

        var ticker = ""
        val token = "fVvtN4WFtkrPTVerbUHG9F"

        etTicker.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // Você pode deixar vazio se não precisar fazer nada aqui
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                ticker = s.toString()
            }

            override fun afterTextChanged(s: Editable?) {
                CoroutineScope(Dispatchers.IO).launch {
                    try {
                        val url = URL("https://brapi.dev/api/quote/$ticker?token=$token")
                        val conn = url.openConnection() as HttpsURLConnection
                        conn.requestMethod = "GET"
                        conn.connect()

                        val response = conn.inputStream.bufferedReader().use { it.readText() }
                        val json = JSONObject(response)
                        val ativo = json.getJSONArray("results").getJSONObject(0)

                        val shortName = ativo.getString("shortName")
                        val currency = ativo.getString("currency")
                        val marketPrice = ativo.getDouble("regularMarketPrice")
                        val marketPreviousClose = ativo.getDouble("regularMarketPreviousClose")
                        val marketChange = ativo.getDouble("regularMarketChange")
                        val marketChangePercent = ativo.getDouble("regularMarketChangePercent")
                        val dayRange = ativo.getString("regularMarketDayRange")
                        val fiftyTwoWeekRange = ativo.getString("fiftyTwoWeekRange")
                        val logoUrl = ativo.getString("logourl")

                        runOnUiThread {
                            tvShortName.text = "Nome curto: $shortName"
                            tvCurrency.text = "Moeda: $currency"
                            tvMarketPrice.text = "Preço atual: R$ $marketPrice"
                            tvMarketPreviousClose.text = "Fechamento anterior: R$ $marketPreviousClose"
                            tvMarketChange.text = "Variação absoluta: R$ $marketChange"
                            tvMarketChangePercent.text = "Variação %: $marketChangePercent %"
                            tvDayRange.text = "Intervalo do dia: $dayRange"
                            tvFiftyTwoWeekRange.text = "Intervalo 52 semanas: $fiftyTwoWeekRange"
                            ivLogo.load(logoUrl) {
                                crossfade(true)
                                decoderFactory(SvgDecoder.Factory())
                                scale(Scale.FILL) // ou Scale.FIT
                            }

                            conn.disconnect()
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
            }
        }
        })
    }
}