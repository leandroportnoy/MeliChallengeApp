# 📦 MeliChallengeApp

App construído para o desafio técnico da empresa **Mercado Livre**.  
A proposta foi desenvolver uma aplicação com estrutura limpa, testes automatizados e uso de boas práticas como Clean Architecture e DDD.

## ✍️ Sobre o Projeto

> MeliChallengeApp - app construído para o desafio da empresa MERCADO LIVRE.  
> O projeto compõe as informações acima e também algumas instruções de testes.  
> Utilizei a estratégia de usar os arquivos json como fonte de dados para listagem dos itens do app.


---

## 🧱 Arquitetura e Organização

O projeto segue a arquitetura em camadas com **DDD (Domain-Driven Design)**, separado em módulos conforme responsabilidades.

### 📁 Estrutura de Módulos

```
MeliChallengeApp/
├── app/              # Módulo principal: inicialização do app e injeção de dependência
├── core/             # Camada de domínio: modelos, interfaces e usecases
├── data/             # Repositórios e data sources (Local JSON)
├── features/         # Telas e ViewModels (Compose)
├── ui/               # Componentes visuais reutilizáveis (Jetpack Compose)
├── common/           # Extensões, utilitários e helpers
└── buildSrc/         # Configurações centralizadas (libs.versions.toml)
```

---

## 💾 Fonte de Dados

A aplicação utiliza **arquivos `.json`** como base de dados local, simulando as chamadas da API Mercado Livre.  
Esses arquivos estão localizados em `assets/` no módulo `data`.

### Arquivos de exemplo:
- `item-MLA1234.json` – detalhes do produto
- `item-MLA1234-description.json` – descrição do produto
- `item-MLA1234-category.json` – categoria do produto

---

## 🧪 Testes Automatizados

### ✅ Testes Unitários

Foram implementados testes nos módulos `core`, `data`, `features` e `ui`, incluindo:

- UseCases (`ListProductsUseCaseImpl`, `ProductDetailsUseCaseImpl`)
- Repositórios (`ProductsRepositoryImpl`)
- Utilitários (`toBrazilianCurrency()`)
- Componentes de UI (`BackButton`, `Logo`, `ProductListItemCard`, `SearchBar`, `ShimmerImage`)

### ✅ Testes Instrumentados

Foram criados testes com `Compose UI Test` para validar os comportamentos visuais e ações de tela:

- Busca de produtos
- Botão "Voltar"
- Exibição de imagens e textos
- Carregamento de itens da lista

---

## ▶️ Como Rodar os Testes

### 🔧 Testes Unitários (todos os módulos)

```bash
./gradlew testDebugUnitTest
```

### 📱 Testes Instrumentados (em emulador/dispositivo)

```bash
./gradlew connectedDebugAndroidTest
```

---

## 📊 Cobertura de Código com Jacoco

Jacoco foi configurado para gerar cobertura unitária por módulo e uma visão geral do projeto.

### 🧪 Rodar cobertura total do projeto:

```bash
./gradlew testDebugUnitTest jacocoFullReport
```

### 📂 Relatório final estará em:

```
build/reports/jacoco/jacocoFullReport/html/index.html
```

Ou individualmente por módulo:

```
<module>/build/outputs/unit_test_code_coverage/debugUnitTest/index.html
```

---

## 🛠️ Tecnologias e Bibliotecas

- **Kotlin 2.0.0**
- **Jetpack Compose**
- **Android Gradle Plugin 8.8.2**
- **Hilt (DI)**
- **Navigation Compose**
- **Coil (AsyncImage)**
- **Accompanist (Shimmer, Placeholder)**
- **MockK** e **JUnit** para testes
- **Jacoco** para cobertura de testes

---