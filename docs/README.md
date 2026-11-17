
# Sistema de Gestão de Biblioteca Universitária — TP1

Projeto de Bloco: Desenvolvimento Back-End (Java 21) — **Trabalho realizado em dupla**.

**Integrantes da dupla:**
- Ibrahim Nagib Karam Neto
- Wallace Clem Fernandes dos Santos

## Sobre o Projeto
Protótipo inicial (standalone e procedural) de um sistema de biblioteca universitária desenvolvido em dupla para validar o levantamento de requisitos, a modelagem UML e as regras de negócio de empréstimo, devolução, cálculo de multa e reserva antes da transição para Orientação a Objetos e Spring Boot nos próximos TPs.

## Estrutura do Repositório
- **`docs/`**: Documento completo em PDF (Requisitos Funcionais e Não Funcionais, Cenários de Uso e Justificativa de Escopo/Suposições) e a imagem do Diagrama de Casos de Uso em UML.
- **`src/`**: Código-fonte `BibliotecaTP1.java` implementado em Java 21 sem Orientação a Objetos (utilizando tipos primitivos, arrays e estruturas de controle).

## Como Executar
1. Certifique-se de ter o **JDK 21** instalado.
2. Compile e execute o arquivo principal pelo terminal ou IDE (IntelliJ IDEA):
   ```bash
   javac src/BibliotecaTP1.java
   java -cp src BibliotecaTP1
