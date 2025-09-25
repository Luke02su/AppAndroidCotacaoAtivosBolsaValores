# Cotações de Ativos da Bolsa de Valores 📈💰  

[![Kotlin](https://img.shields.io/badge/Linguagem-Kotlin-orange?logo=kotlin)](https://kotlinlang.org/)  
[![Android Studio](https://img.shields.io/badge/IDE-Android_Studio-brightgreen?logo=android-studio)](https://developer.android.com/studio)  
[![License](https://img.shields.io/badge/License-MIT-blue)](LICENSE)  

---

## 📌 Projeto
Este aplicativo Android, desenvolvido em **Kotlin**, permite consultar **cotações de ativos da B3 e de bolsas internacionais**, consumindo dados em tempo real da **API [brapi.dev](https://brapi.dev/)**.

O app possui um **design moderno e responsivo**, exibindo informações detalhadas como preço atual, moeda, fechamento anterior, variação diária e intervalo de 52 semanas. É ideal para investidores e entusiastas do mercado financeiro que desejam acompanhar ativos de forma prática pelo celular.

---

## 🎨 Layout do App

### Tela Principal
- **Campo de entrada (EditText):** digite o ticker do ativo (até 6 caracteres, automaticamente em maiúsculas).  
- **Logo do ativo:** carregada automaticamente via URL (suporte a SVG usando Coil).  
- **Caixa de informações:** apresenta os principais dados do ativo pesquisado.

### Informações exibidas
- Nome curto do ativo  
- Moeda de negociação  
- Preço atual  
- Fechamento anterior  
- Variação absoluta e percentual no dia (**verde positivo, vermelho negativo**)  
- Intervalo diário  
- Intervalo das últimas 52 semanas  

---

## 📱 Prints da Tela

<p align="center">
   <img width="300" height="600" alt="Tela 1" src="https://github.com/user-attachments/assets/caa47ec3-8d09-4ee3-b41e-0c2a9cd8a99b" />
   <img width="300" height="600" alt="Tela 2" src="https://github.com/user-attachments/assets/82f80789-77ea-4fed-a5c7-2cc352731abe" />
   <img width="300" height="600" alt="Tela 3" src="https://github.com/user-attachments/assets/eee5ddb2-ae85-46a7-873d-0bf0b602c73e" />
</p>

---

## ✨ Funcionalidades
| Funcionalidade        | Descrição |
|----------------------|-----------|
| Consulta de Ativos     | Busca informações em tempo real através da API brapi.dev |
| Exibição de Logo       | Carregamento automático do logo do ativo (SVG suportado via Coil) |
| Informações do Ativo   | Nome, moeda, preço, variação diária e intervalos |
| Campo de Entrada       | Aceita apenas tickers (máx. 6 caracteres, maiúsculos) |
| Variação de Preço      | Cor verde para positivo e vermelho para negativo |
| UI Responsiva          | Layout adaptado para diferentes tamanhos de tela |
| Edge-to-Edge           | Uso completo da tela, status bar transparente |
| ScrollView             | Permite rolagem caso a tela ultrapasse o limite visível |

---

## 🔄 Fluxo de Uso
1. Digitar o **ticker** do ativo no campo de entrada.  
2. O app consome a **API brapi.dev** e retorna os dados em tempo real.  
3. As informações são exibidas em um **layout limpo e responsivo**.  
4. A logo do ativo é carregada automaticamente no topo.

### Exemplos de Tickers para teste:
- PETR4 (Petrobras PN)  
- VALE3 (Vale ON)  
- AAPL (Apple - Nasdaq)  
- MSFT (Microsoft - Nasdaq)  

---

## 🛠 Tecnologias Utilizadas
- **Linguagem:** Kotlin  
- **IDE:** Android Studio  
- **API:** [brapi.dev](https://brapi.dev/)  
- **UI Components:** ConstraintLayout, LinearLayout, ScrollView, EditText, TextView, ImageView  
- **Biblioteca de Imagens:** [Coil](https://coil-kt.github.io/coil/) (com suporte a SVG via `SvgDecoder`)  
- **Design:**  
  - Fundo: Azul escuro `#1B263B`  
  - Texto principal: Cinza quase preto `#212121`  
  - Caixa de informações: Drawable estilizado em tons claros  
  - Campos com bordas arredondadas e espaçamento consistente  

---

## 🔧 Boas Práticas Implementadas
- Modularização do código para facilitar manutenção  
- Reutilização de estilos XML para TextView e EditText  
- Input validation: só aceita tickers válidos  
- Layout edge-to-edge para design moderno  
- Responsivo e adaptável a diferentes tamanhos de tela  

---

## 👨‍💻 Autor
**Lucas Samuel Dias**  
- Desenvolvedor Kotlin Android  
- Focado em integração de APIs, UI/UX e aplicativos financeiros  

---

## 📜 Licença
Este projeto está licenciado sob a licença **MIT**. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.

## 📜 Licença
Este projeto está licenciado sob a licença **MIT**. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.
