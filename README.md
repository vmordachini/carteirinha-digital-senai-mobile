# 🪪 Carteirinha Digital SENAI

Este é um projeto mobile desenvolvido em **Kotlin** com **Jetpack Compose**. A aplicação funciona como uma carteirinha de estudante digital para alunos do SENAI-SP, contando agora com integração a uma API REST para autenticação (Login) e busca de dados do perfil do aluno.

## 🚀 Funcionalidades
- **Autenticação:** Sistema de Login integrado com a API, utilizando token JWT armazenado de forma segura localmente.
- **Navegação Inteligente:** Roteamento dinâmico que direciona o utilizador para o Login, Configuração de Perfil ou diretamente para a Carteirinha com base no estado de autenticação.
- **Consumo de API:** Busca de dados atualizados do aluno diretamente do servidor.
- **Identificação Visual:** Exibição do logótipo oficial do SENAI-SP e foto do aluno carregada via URL.
- **QR Code Dinâmico:** Geração automática de QR Code baseado no código de identificação utilizando a biblioteca ZXing.
- **Gestão de Sessão:** Funcionalidade de logout para limpar dados de acesso e retornar à tela inicial.

## 🛠️ Tecnologias e Arquitetura
O projeto segue a arquitetura **MVVM** e utiliza as seguintes tecnologias:
- **UI Framework:** Jetpack Compose com Material Design 3.
- **Navegação:** Jetpack Navigation Compose.
- **Comunicação Web:** Retrofit e OkHttp (com intercetores para injeção de Token JWT).
- **Armazenamento Local:** Jetpack DataStore (Preferências) para tokens e Room Database para persistência.
- **Gestão de Imagens:** Coil para carregamento assíncrono de fotos.
- **Geração de QR Code:** ZXing (Zebra Crossing).

## 📁 Estrutura do Projeto
A aplicação foi modularizada para melhor escalabilidade:
- **`core/`**: Contém o sistema de roteamento (`AppNavHost`, `Routes`), utilitários (`QrCode.kt`) e o design system (Theme, Color, Type).
- **`data/`**: Responsável pela camada de dados. Inclui a base de dados local (`DataStoreManager`), configuração de rede (`RetrofitClient`, APIs, Interceptors) e os repositórios (`AlunoRepository`, `AuthRepository`).
- **`features/`**: Agrupa as funcionalidades principais da app:
  - **`auth/`**: Ecrãs de login e ViewModels de autenticação.
  - **`carteirinha/`**: Ecrã principal do cartão, componentes visuais e ViewModel do aluno.
  - **`configuracao/`**: Ecrã para preenchimento ou edição dos dados do aluno.

## 📥 Instalação e Execução
Para executar este projeto localmente, siga os passos abaixo:
1. **Clone o repositório:**
   `git clone https://github.com/seu-usuario/carteirinha-digital-senai.git`
2. **Configuração da API:** No ficheiro `RetrofitClient.kt`, certifique-se de que a variável `BASE_URL` está a apontar para o servidor correto (atualmente `https://api.exemplo-senai.com.br/v1/`).
3. **Abra no Android Studio:** Certifique-se de estar a utilizar uma versão compatível (Ladybug ou superior). O projeto requer o SDK 36 e utiliza Java 11 / JDK 21.
4. **Sincronize o Gradle:** Aguarde o download das dependências (Retrofit, DataStore, Compose, etc).
5. **Execute a App:** Ligue um dispositivo físico ou utilize um emulador com Android 7.0 (API 24) ou superior.
