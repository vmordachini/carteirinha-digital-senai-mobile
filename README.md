# 🪪 Carteirinha Digital SENAI

Aplicativo Android nativo desenvolvido para servir como a carteirinha digital de estudante dos alunos do SENAI-SP. O projeto utiliza as práticas mais modernas de desenvolvimento Android, focado em uma interface puramente declarativa e arquitetura robusta.

---

## ✨ Funcionalidades (Features)

### 🔐 Autenticação e Sessão
- [x] Tela de Login (Matrícula/Email e Senha)
- [x] Comunicação via API REST (POST `/auth/login`)
- [x] Interceptação de requisições para injeção de Token no Header (`AuthInterceptor`)
- [x] Armazenamento local e seguro do Token JWT
- [x] Funcionalidade de Logout (Limpeza de token e cache)
- [x] Redirecionamento inteligente baseado no estado da sessão (Logado vs Deslogado)

### 👤 Perfil do Aluno
- [x] Tela de Configuração/Preenchimento de Dados
- [x] Inserção de Nome, Curso e Matrícula
- [x] Seleção de foto de perfil (Galeria do dispositivo)
- [x] Carregamento de imagem de fallback/placeholder

### 🎟️ Carteirinha Digital
- [x] Interface baseada no Material Design 3
- [x] Exibição dos dados consolidados do aluno
- [x] Geração dinâmica de QR Code na tela a partir de texto/matrícula
- [x] Bloqueio de retorno para tela de login ao usar a carteirinha (Gestão de pilha de navegação)

---

## 🛠️ Tecnologias e Bibliotecas Utilizadas

- **Linguagem & UI**
  - [x] Kotlin
  - [x] Jetpack Compose (Construção de UI Declarativa)
  - [x] Navigation Compose (Roteamento de Telas)
  - [x] Material Design 3

- **Arquitetura & Padrões**
  - [x] MVVM (Model-View-ViewModel)
  - [x] StateFlow / Coroutines (Programação Assíncrona e Reativa)
  - [x] Injeção de Dependência (Factory manual / *A avaliar uso de Hilt/Dagger futuramente*)

- **Comunicação & Rede**
  - [x] Retrofit2
  - [x] OkHttp3 (com Interceptors)
  - [x] GSON (Serialização/Desserialização JSON)

- **Armazenamento Local & Mídia**
  - [x] Preferences DataStore (Persistência do JWT)
  - [x] Room Database (Configurado para futura persistência da entidade `Aluno`)
  - [x] Coil (Carregamento assíncrono de imagens)
  - [x] ZXing Core (Geração de matriz do QR Code)

---

## ⚙️ Como Executar o Projeto

Para rodar este projeto na sua máquina local, certifique-se de completar os seguintes passos:

- [ ] Instalar o **Android Studio** (versão mais recente recomendada).
- [ ] Clonar este repositório: `git clone <link-do-repositorio>`.
- [ ] Abrir o projeto no Android Studio.
- [ ] Aguardar o *Sync* do Gradle (baixar todas as dependências marcadas no `build.gradle.kts`).
- [ ] Configurar a **URL Base da API** no arquivo `RetrofitClient.kt`.
- [ ] Executar o projeto (`Shift + F10`) em um Emulador (AVD) ou Smartphone Físico (via USB Debugging/Wi-Fi).

---

## 📝 Backlog e Próximos Passos (To-Do)

Aqui estão as próximas melhorias mapeadas para o ciclo de vida do projeto:

- [ ] **Cache Offline:** Finalizar a implementação do repositório local usando o Room para permitir que a carteirinha seja aberta mesmo sem internet.
- [ ] **Tratamento de Erros:** Melhorar o feedback visual na UI em casos de timeout da API ou falha de rede (ex: Snackbars).
- [ ] **Testes Unitários:** Implementar testes para `AuthViewModel` e `AlunoViewModel`.
- [ ] **Testes de UI:** Criar testes instrumentados para as telas em Jetpack Compose.
- [ ] **Injeção de Dependência:** Migrar a criação manual de ViewModels (Factories) para o **Hilt**.
- [ ] **Segurança:** Implementar criptografia no DataStore (Preferences DataStore -> EncryptedSharedPreferences/Proto DataStore) para aumentar a segurança do JWT.
