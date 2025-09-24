package com.example.cotaodosativosdabolsa

import android.graphics.Color
import android.graphics.Insets.add
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import coil.decode.SvgDecoder
import coil.load
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.net.URL
import java.util.Locale
import javax.net.ssl.HttpsURLConnection

private lateinit var tvMarketChange: TextView
private lateinit var tvMarketChangePercent: TextView

private var marketChange = 0.00

private var marketChangePercent = 0.00

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etTicker = findViewById<EditText>(R.id.etTicker)
        val tvShortName = findViewById<TextView>(R.id.tvShortName)
        val tvCurrency = findViewById<TextView>(R.id.tvCurrency)
        val tvMarketPrice = findViewById<TextView>(R.id.tvMarketPrice)
        val tvMarketPreviousClose = findViewById<TextView>(R.id.tvMarketPreviousClose)
        tvMarketChange = findViewById(R.id.tvMarketChange)
        tvMarketChangePercent = findViewById(R.id.tvMarketChangePercent)
        val tvDayRange = findViewById<TextView>(R.id.tvDayRange)
        val tvFiftyTwoWeekRange = findViewById<TextView>(R.id.tvFiftyTwoWeekRange)
        val ivLogo = findViewById<ImageView>(R.id.ivLogo)
        ivLogo.setImageResource(R.drawable.icon_app)

        var ticker = ""
        val token = "fVvtN4WFtkrPTVerbUHG9F"

        etTicker.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                if (s.toString().isEmpty()) {
                    Toast.makeText(this@MainActivity, "Digite corretamente o ticker do ativo que deseja visualizar.", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                ticker = s.toString().uppercase(Locale.getDefault())
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
                        marketChange = ativo.getDouble("regularMarketChange")
                        marketChangePercent = ativo.getDouble("regularMarketChangePercent")
                        val dayRange = ativo.getString("regularMarketDayRange")
                        val fiftyTwoWeekRange = ativo.getString("fiftyTwoWeekRange")
                        val logoUrl = ativo.getString("logourl")

                        runOnUiThread {
                            tvShortName.text = "Nome curto: $shortName"
                            tvCurrency.text = "Moeda: $currency"
                            tvMarketPrice.text = "Preço atual: $currency $marketPrice"
                            tvMarketPreviousClose.text = "Fechamento anterior: $currency $marketPreviousClose"
                            tvMarketChange.text = "Variação do dia: $currency $marketChange"
                            tvMarketChangePercent.text = "Variação do dia: $marketChangePercent%"
                            tvDayRange.text = "Intervalo do dia ($currency): $dayRange"
                            tvFiftyTwoWeekRange.text = "Intervalo de 52 semanas ($currency): $fiftyTwoWeekRange"
                            changeColorVariation()
                            ivLogo.load(logoUrl) {
                                crossfade(true)
                                decoderFactory(SvgDecoder.Factory())
                            }

                            if (conn.responseCode != 200) {
                                Toast.makeText(this@MainActivity, "Erro ao obter dados do ativo: verifique conexão à internet.", Toast.LENGTH_SHORT).show()
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

private fun changeColorVariation() {
    if (marketChange > 0 && marketChangePercent > 0.00) {
        tvMarketChange.setTextColor(Color.parseColor("#2E7D32"))
        tvMarketChangePercent.setTextColor(Color.parseColor("#2E7D32"))
    } else {
        tvMarketChange.setTextColor(Color.parseColor("#C62828"))
        tvMarketChangePercent.setTextColor(Color.parseColor("#C62828"))
    }
}