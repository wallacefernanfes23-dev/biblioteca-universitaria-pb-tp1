
import java.util.Scanner;

public class BibliotecaTP1 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Parâmetros de prazo, limite e multa
        final int PRAZO_DEVOLUCAO_DIAS = 14;
        final int LIMITE_EMPRESTIMOS_ATIVOS = 3;
        final double VALOR_MULTA_DIARIA = 2.50;
        final int CAPACIDADE_ACERVO = 10;

        // Arrays para armazenar os dados dos livros e exemplares
        String[] titulos = new String[CAPACIDADE_ACERVO];
        String[] autores = new String[CAPACIDADE_ACERVO];
        String[] isbns = new String[CAPACIDADE_ACERVO];
        String[] patrimonios = new String[CAPACIDADE_ACERVO];
        int[] totaisExemplares = new int[CAPACIDADE_ACERVO];
        int[] disponiveis = new int[CAPACIDADE_ACERVO];
        int[] emprestados = new int[CAPACIDADE_ACERVO];
        boolean[] bomEstado = new boolean[CAPACIDADE_ACERVO];
        boolean[] reservaAtiva = new boolean[CAPACIDADE_ACERVO];
        String[] dataReserva = new String[CAPACIDADE_ACERVO];

        // Controla se o membro ativo já está com um exemplar deste título emprestado
        boolean[] membroPossuiLivro = new boolean[CAPACIDADE_ACERVO];

        // Carga inicial do acervo
        titulos[0] = "Engenharia de Software Moderna";
        autores[0] = "Marco Tulio Valente";
        isbns[0] = "978-65-00-12345-6";
        patrimonios[0] = "PAT-001";
        totaisExemplares[0] = 3;
        disponiveis[0] = 2;
        emprestados[0] = 1;
        bomEstado[0] = true;
        reservaAtiva[0] = false;
        dataReserva[0] = "";
        membroPossuiLivro[0] = false;

        titulos[1] = "Código Limpo (Clean Code)";
        autores[1] = "Robert C. Martin";
        isbns[1] = "978-85-7608-267-5";
        patrimonios[1] = "PAT-002";
        totaisExemplares[1] = 2;
        disponiveis[1] = 2;
        emprestados[1] = 0;
        bomEstado[1] = true;
        reservaAtiva[1] = false;
        dataReserva[1] = "";
        membroPossuiLivro[1] = false;

        titulos[2] = "Java Efetivo";
        autores[2] = "Joshua Bloch";
        isbns[2] = "978-85-508-0462-0";
        patrimonios[2] = "PAT-003";
        totaisExemplares[2] = 1;
        disponiveis[2] = 0;
        emprestados[2] = 1;
        bomEstado[2] = true;
        reservaAtiva[2] = false;
        dataReserva[2] = "";
        membroPossuiLivro[2] = false;

        int quantidadeLivros = 3;

        // Dados do membro ativo
        String nomeMembroCadastrado = "Fernando Santos";
        String matriculaMembro = "2026100";
        String tipoMembro = "Aluno";
        boolean cadastroMembroValido = true;
        int emprestimosAtivosMembro = 0;
        double multasPendentes = 0.0;

        System.out.println("=======================================================");
        System.out.println("   SISTEMA DE GESTÃO DE BIBLIOTECA UNIVERSITÁRIA");
        System.out.println("=======================================================");

        System.out.print("Informe o seu nome: ");
        String nomeUsuarioSessao = scanner.nextLine();

        // Seleção de perfil de acesso
        int perfil = 0;
        while (perfil != 1 && perfil != 2) {
            System.out.println("\nOlá, " + nomeUsuarioSessao + "! Escolha seu perfil de acesso:");
            System.out.println("1 - Membro (Aluno, Professor ou Funcionário)");
            System.out.println("2 - Bibliotecário / Administrador");
            System.out.print("Opção: ");

            if (scanner.hasNextInt()) {
                perfil = scanner.nextInt();
                scanner.nextLine();
                if (perfil != 1 && perfil != 2) {
                    System.out.println("Opção inválida. Digite 1 ou 2.");
                }
            } else {
                System.out.println("Digite apenas números.");
                scanner.nextLine();
            }
        }

        // Loop principal do sistema
        while (true) {
            System.out.println("\n-------------------------------------------------------");
            if (perfil == 1) {
                System.out.println("MENU DO MEMBRO | Usuário: " + nomeUsuarioSessao);
                System.out.println("1 - Consultar Acervo e Disponibilidade");
                System.out.println("2 - Solicitar Empréstimo");
                System.out.println("3 - Solicitar Devolução");
                System.out.println("4 - Realizar Reserva de Livro");
                System.out.println("9 - Trocar Perfil (Ir para Bibliotecário/Administrador)");
                System.out.println("0 - Sair do Sistema");
            } else {
                System.out.println("MENU DO BIBLIOTECÁRIO / ADMINISTRADOR | Usuário: " + nomeUsuarioSessao);
                System.out.println("1 - Cadastrar Novo Livro e Exemplares");
                System.out.println("2 - Cadastrar Membro");
                System.out.println("3 - Processar Empréstimo");
                System.out.println("4 - Processar Devolução e Multa");
                System.out.println("5 - Consultar e Quitar Multas");
                System.out.println("6 - Alterar Estado de Conservação do Exemplar");
                System.out.println("7 - Consultar Acervo Completo");
                System.out.println("9 - Trocar Perfil (Ir para Membro)");
                System.out.println("0 - Sair do Sistema");
            }
            System.out.println("-------------------------------------------------------");

            System.out.print("Digite a opção desejada: ");
            if (!scanner.hasNextInt()) {
                System.out.println("Entrada inválida. Digite apenas números.");
                scanner.nextLine();
                continue;
            }

            int opcao = scanner.nextInt();
            scanner.nextLine();

            if (opcao == 0) {
                System.out.println("\nEncerrando o sistema. Até logo, " + nomeUsuarioSessao + "!");
                break;
            }

            if (opcao == 9) {
                perfil = (perfil == 1) ? 2 : 1;
                continue;
            }

            // Fluxo de operações do Membro
            if (perfil == 1) {
                switch (opcao) {
                    case 1:
                        // Exibe apenas informações relevantes ao membro (sem ISBN e patrimônio)
                        System.out.println("\n--- ACERVO DA BIBLIOTECA ---");
                        for (int i = 0; i < quantidadeLivros; i++) {
                            String status = (disponiveis[i] > 0 && bomEstado[i]) ? "DISPONÍVEL" : "INDISPONÍVEL";
                            System.out.println((i + 1) + ". [" + status + "] " + titulos[i] + " | Autor: " + autores[i]);
                            System.out.println("   Exemplares disponíveis: " + disponiveis[i] + " de " + totaisExemplares[i]);
                        }
                        System.out.print("\nDigite 0 para voltar ao menu: ");
                        while (!scanner.hasNextInt() || scanner.nextInt() != 0) {
                            scanner.nextLine();
                            System.out.print("Digite 0 para voltar ao menu: ");
                        }
                        scanner.nextLine();
                        break;

                    case 2:
                        while (true) {
                            System.out.println("\n--- SOLICITAR EMPRÉSTIMO (Ativos: " + emprestimosAtivosMembro + "/" + LIMITE_EMPRESTIMOS_ATIVOS + ") ---");
                            for (int i = 0; i < quantidadeLivros; i++) {
                                String status = (disponiveis[i] > 0 && bomEstado[i]) ? "DISPONÍVEL" : "INDISPONÍVEL";
                                System.out.println((i + 1) + " - [" + status + "] " + titulos[i] + " (Livres: " + disponiveis[i] + ")");
                            }
                            System.out.print("Escolha o número do livro (ou 0 para voltar ao menu): ");

                            if (!scanner.hasNextInt()) {
                                System.out.println("Digite apenas números.");
                                scanner.nextLine();
                                continue;
                            }
                            int escolha = scanner.nextInt();
                            scanner.nextLine();

                            if (escolha == 0) break;

                            int idxEmp = escolha - 1;
                            if (idxEmp < 0 || idxEmp >= quantidadeLivros) {
                                System.out.println("Livro não encontrado.");
                            } else if (!cadastroMembroValido) {
                                System.out.println("Empréstimo negado: cadastro inválido.");
                            } else if (membroPossuiLivro[idxEmp]) {
                                System.out.println("Empréstimo negado: você já possui um exemplar deste livro emprestado.");
                            } else if (emprestimosAtivosMembro >= LIMITE_EMPRESTIMOS_ATIVOS) {
                                System.out.println("Empréstimo negado: limite de " + LIMITE_EMPRESTIMOS_ATIVOS + " livros atingido.");
                            } else if (disponiveis[idxEmp] <= 0 || !bomEstado[idxEmp]) {
                                System.out.println("Empréstimo negado: exemplar indisponível ou danificado.");
                            } else {
                                emprestimosAtivosMembro++;
                                disponiveis[idxEmp]--;
                                emprestados[idxEmp]++;
                                membroPossuiLivro[idxEmp] = true;
                                reservaAtiva[idxEmp] = false;
                                System.out.println("Sucesso! Você retirou '" + titulos[idxEmp] + "'. Prazo: " + PRAZO_DEVOLUCAO_DIAS + " dias corridos.");
                            }
                        }
                        break;

                    case 3:
                        while (true) {
                            System.out.println("\n--- SOLICITAR DEVOLUÇÃO ---");
                            for (int i = 0; i < quantidadeLivros; i++) {
                                System.out.println((i + 1) + " - " + titulos[i] + " (Emprestados: " + emprestados[i] + ")");
                            }
                            System.out.print("Escolha o número do livro para devolver (ou 0 para voltar ao menu): ");

                            if (!scanner.hasNextInt()) {
                                System.out.println("Digite apenas números.");
                                scanner.nextLine();
                                continue;
                            }
                            int escolhaDev = scanner.nextInt();
                            scanner.nextLine();

                            if (escolhaDev == 0) break;

                            int idxDev = escolhaDev - 1;
                            if (idxDev < 0 || idxDev >= quantidadeLivros || emprestados[idxDev] <= 0) {
                                System.out.println("Não há exemplares emprestados deste livro.");
                            } else {
                                System.out.print("Há quantos dias corridos o livro foi retirado? ");
                                if (!scanner.hasNextInt()) {
                                    System.out.println("Quantidade de dias inválida.");
                                    scanner.nextLine();
                                    continue;
                                }
                                int diasCorridos = scanner.nextInt();
                                scanner.nextLine();

                                if (diasCorridos < 0) {
                                    System.out.println("A quantidade de dias não pode ser negativa.");
                                    continue;
                                }

                                if (emprestimosAtivosMembro > 0) emprestimosAtivosMembro--;
                                emprestados[idxDev]--;
                                disponiveis[idxDev]++;
                                membroPossuiLivro[idxDev] = false;

                                if (diasCorridos > PRAZO_DEVOLUCAO_DIAS) {
                                    int diasAtraso = diasCorridos - PRAZO_DEVOLUCAO_DIAS;
                                    double multaGerada = diasAtraso * VALOR_MULTA_DIARIA;
                                    multasPendentes += multaGerada;
                                    System.out.printf("Devolvido com %d dia(s) de atraso. Multa gerada: R$ %.2f%n", diasAtraso, multaGerada);
                                } else {
                                    System.out.println("Devolução concluída dentro do prazo!");
                                }
                            }
                        }
                        break;

                    case 4:
                        while (true) {
                            System.out.println("\n--- REALIZAR RESERVA DE LIVRO ---");
                            for (int i = 0; i < quantidadeLivros; i++) {
                                System.out.println((i + 1) + " - " + titulos[i] + " (Emprestados: " + emprestados[i] + "/" + totaisExemplares[i] + ")");
                            }
                            System.out.print("Escolha o número do livro para reservar (ou 0 para voltar ao menu): ");

                            if (!scanner.hasNextInt()) {
                                System.out.println("Digite apenas números.");
                                scanner.nextLine();
                                continue;
                            }
                            int escolhaRes = scanner.nextInt();
                            scanner.nextLine();

                            if (escolhaRes == 0) break;

                            int idxRes = escolhaRes - 1;
                            if (idxRes < 0 || idxRes >= quantidadeLivros) {
                                System.out.println("Livro inválido.");
                            } else if (!cadastroMembroValido) {
                                System.out.println("Reserva bloqueada: cadastro inválido.");
                            } else if (membroPossuiLivro[idxRes]) {
                                System.out.println("Reserva bloqueada: você já está com este livro emprestado.");
                            } else if (emprestados[idxRes] == 0) {
                                System.out.println("Reserva bloqueada: todos os exemplares estão disponíveis. Solicite o empréstimo diretamente.");
                            } else if (reservaAtiva[idxRes]) {
                                System.out.println("Já existe uma reserva ativa para este livro em " + dataReserva[idxRes] + ".");
                            } else {
                                System.out.print("Informe a data da reserva (dd/mm/aaaa): ");
                                dataReserva[idxRes] = scanner.nextLine();
                                reservaAtiva[idxRes] = true;
                                System.out.println("Reserva de '" + titulos[idxRes] + "' confirmada!");
                            }
                        }
                        break;

                    default:
                        System.out.println("Opção inválida.");
                }
            }

            // Fluxo de operações do Bibliotecário / Administrador
            else {
                switch (opcao) {
                    case 1:
                        System.out.println("\n--- CADASTRAR NOVO LIVRO E EXEMPLARES ---");
                        if (quantidadeLivros >= CAPACIDADE_ACERVO) {
                            System.out.println("Limite do acervo atingido.");
                            break;
                        }

                        System.out.print("Título do livro (ex: Arquitetura Limpa) ou 0 para voltar: ");
                        String novoTitulo = scanner.nextLine();
                        if (novoTitulo.equals("0")) break;

                        titulos[quantidadeLivros] = novoTitulo;

                        System.out.print("Autor (ex: Robert C. Martin): ");
                        autores[quantidadeLivros] = scanner.nextLine();

                        System.out.print("ISBN (ex: 978-85-508-0460-6): ");
                        isbns[quantidadeLivros] = scanner.nextLine();

                        System.out.print("Código de patrimônio (ex: PAT-004): ");
                        patrimonios[quantidadeLivros] = scanner.nextLine();

                        System.out.print("Quantidade de exemplares físicos (ex: 3): ");
                        if (!scanner.hasNextInt()) {
                            System.out.println("Quantidade inválida.");
                            scanner.nextLine();
                            break;
                        }
                        int qtd = scanner.nextInt();
                        scanner.nextLine();

                        if (qtd <= 0) {
                            System.out.println("A quantidade precisa ser maior que zero.");
                            break;
                        }

                        totaisExemplares[quantidadeLivros] = qtd;
                        disponiveis[quantidadeLivros] = qtd;
                        emprestados[quantidadeLivros] = 0;
                        bomEstado[quantidadeLivros] = true;
                        reservaAtiva[quantidadeLivros] = false;
                        dataReserva[quantidadeLivros] = "";
                        membroPossuiLivro[quantidadeLivros] = false;
                        quantidadeLivros++;

                        System.out.println("Livro cadastrado com sucesso!");
                        break;

                    case 2:
                        System.out.println("\n--- CADASTRAR MEMBRO ---");
                        System.out.print("Nome completo do membro (ex: Mariana Oliveira) ou 0 para voltar: ");
                        String novoMembro = scanner.nextLine();
                        if (novoMembro.equals("0")) break;

                        nomeMembroCadastrado = novoMembro;

                        System.out.print("Matrícula (ex: 2026205): ");
                        matriculaMembro = scanner.nextLine();

                        System.out.print("Tipo (ex: Aluno, Professor ou Funcionário): ");
                        tipoMembro = scanner.nextLine();

                        cadastroMembroValido = true;
                        emprestimosAtivosMembro = 0;
                        multasPendentes = 0.0;
                        for (int i = 0; i < quantidadeLivros; i++) {
                            membroPossuiLivro[i] = false;
                        }
                        System.out.println("Membro " + nomeMembroCadastrado + " (" + tipoMembro + ") cadastrado!");
                        break;

                    case 3:
                        while (true) {
                            System.out.println("\n--- PROCESSAR EMPRÉSTIMO (Membro: " + nomeMembroCadastrado + " | Ativos: " + emprestimosAtivosMembro + "/" + LIMITE_EMPRESTIMOS_ATIVOS + ") ---");
                            for (int i = 0; i < quantidadeLivros; i++) {
                                String status = (disponiveis[i] > 0 && bomEstado[i]) ? "DISPONÍVEL" : "INDISPONÍVEL";
                                System.out.println((i + 1) + " - [" + status + "] " + titulos[i] + " (Livres: " + disponiveis[i] + ")");
                            }
                            System.out.print("Escolha o número do livro (ou 0 para voltar ao menu): ");

                            if (!scanner.hasNextInt()) {
                                System.out.println("Digite apenas números.");
                                scanner.nextLine();
                                continue;
                            }
                            int escolhaBibEmp = scanner.nextInt();
                            scanner.nextLine();

                            if (escolhaBibEmp == 0) break;

                            int idxBibEmp = escolhaBibEmp - 1;
                            if (idxBibEmp < 0 || idxBibEmp >= quantidadeLivros) {
                                System.out.println("Livro inválido.");
                            } else if (membroPossuiLivro[idxBibEmp]) {
                                System.out.println("Bloqueado: o membro já possui um exemplar deste livro emprestado.");
                            } else if (emprestimosAtivosMembro >= LIMITE_EMPRESTIMOS_ATIVOS) {
                                System.out.println("Bloqueado: membro atingiu o limite de " + LIMITE_EMPRESTIMOS_ATIVOS + " empréstimos.");
                            } else if (disponiveis[idxBibEmp] <= 0 || !bomEstado[idxBibEmp]) {
                                System.out.println("Bloqueado: exemplar indisponível ou danificado.");
                            } else {
                                emprestimosAtivosMembro++;
                                disponiveis[idxBibEmp]--;
                                emprestados[idxBibEmp]++;
                                membroPossuiLivro[idxBibEmp] = true;
                                reservaAtiva[idxBibEmp] = false;
                                System.out.println("Empréstimo registrado! Prazo: " + PRAZO_DEVOLUCAO_DIAS + " dias corridos.");
                            }
                        }
                        break;

                    case 4:
                        while (true) {
                            System.out.println("\n--- PROCESSAR DEVOLUÇÃO E MULTA ---");
                            for (int i = 0; i < quantidadeLivros; i++) {
                                System.out.println((i + 1) + " - " + titulos[i] + " (Emprestados: " + emprestados[i] + ")");
                            }
                            System.out.print("Escolha o número do livro devolvido (ou 0 para voltar ao menu): ");

                            if (!scanner.hasNextInt()) {
                                System.out.println("Digite apenas números.");
                                scanner.nextLine();
                                continue;
                            }
                            int escolhaBibDev = scanner.nextInt();
                            scanner.nextLine();

                            if (escolhaBibDev == 0) break;

                            int idxBibDev = escolhaBibDev - 1;
                            if (idxBibDev < 0 || idxBibDev >= quantidadeLivros || emprestados[idxBibDev] <= 0) {
                                System.out.println("Não há empréstimos ativos para este livro.");
                                continue;
                            }

                            System.out.print("Quantos dias corridos se passaram desde a retirada? (ex: 12 ou 19): ");
                            if (!scanner.hasNextInt()) {
                                System.out.println("Número de dias inválido.");
                                scanner.nextLine();
                                continue;
                            }
                            int diasCorridos = scanner.nextInt();
                            scanner.nextLine();

                            System.out.print("O exemplar voltou em boas condições? (true/false - ex: true): ");
                            if (!scanner.hasNextBoolean()) {
                                System.out.println("Digite apenas true ou false.");
                                scanner.nextLine();
                                continue;
                            }
                            boolean voltouIntacto = scanner.nextBoolean();
                            scanner.nextLine();

                            if (emprestimosAtivosMembro > 0) emprestimosAtivosMembro--;
                            emprestados[idxBibDev]--;
                            disponiveis[idxBibDev]++;
                            membroPossuiLivro[idxBibDev] = false;
                            bomEstado[idxBibDev] = voltouIntacto;

                            if (diasCorridos > PRAZO_DEVOLUCAO_DIAS) {
                                int diasAtraso = diasCorridos - PRAZO_DEVOLUCAO_DIAS;
                                double multaGerada = diasAtraso * VALOR_MULTA_DIARIA;
                                multasPendentes += multaGerada;
                                System.out.printf("Devolução com %d dia(s) de atraso. Multa gerada: R$ %.2f%n", diasAtraso, multaGerada);
                            } else {
                                System.out.println("Devolução concluída dentro do prazo. Sem multas.");
                            }

                            if (!bomEstado[idxBibDev]) {
                                System.out.println("Aviso: exemplar marcado como danificado.");
                            }
                        }
                        break;

                    case 5:
                        System.out.println("\n--- CONSULTAR E QUITAR MULTAS ---");
                        System.out.printf("Membro: %s | Saldo de multas: R$ %.2f%n", nomeMembroCadastrado, multasPendentes);
                        if (multasPendentes > 0) {
                            System.out.print("Digite 1 para quitar a multa (ou 0 para voltar ao menu): ");
                            if (scanner.hasNextInt() && scanner.nextInt() == 1) {
                                multasPendentes = 0.0;
                                System.out.println("Multa quitada com sucesso!");
                            }
                            scanner.nextLine();
                        } else {
                            System.out.print("Digite 0 para voltar ao menu: ");
                            while (!scanner.hasNextInt() || scanner.nextInt() != 0) {
                                scanner.nextLine();
                                System.out.print("Digite 0 para voltar ao menu: ");
                            }
                            scanner.nextLine();
                        }
                        break;

                    case 6:
                        while (true) {
                            System.out.println("\n--- ALTERAR CONSERVAÇÃO DO EXEMPLAR ---");
                            for (int i = 0; i < quantidadeLivros; i++) {
                                System.out.println((i + 1) + " - " + titulos[i] + " (" + patrimonios[i] + " - "
                                        + (bomEstado[i] ? "Adequado" : "Danificado") + ")");
                            }
                            System.out.print("Escolha o número do livro (ou 0 para voltar ao menu): ");

                            if (!scanner.hasNextInt()) {
                                System.out.println("Digite apenas números.");
                                scanner.nextLine();
                                continue;
                            }
                            int escolhaEst = scanner.nextInt();
                            scanner.nextLine();

                            if (escolhaEst == 0) break;

                            int idxEst = escolhaEst - 1;
                            if (idxEst >= 0 && idxEst < quantidadeLivros) {
                                bomEstado[idxEst] = !bomEstado[idxEst];
                                System.out.println("Estado alterado para: " + (bomEstado[idxEst] ? "Adequado" : "Danificado"));
                            } else {
                                System.out.println("Livro inválido.");
                            }
                        }
                        break;

                    case 7:
                        // Exibe todos os dados técnicos (ISBN, Patrimônio, Conservação) para o Administrador
                        System.out.println("\n--- CONSULTA DE ACERVO COMPLETO ---");
                        for (int i = 0; i < quantidadeLivros; i++) {
                            String status = (disponiveis[i] > 0 && bomEstado[i]) ? "DISPONÍVEL" : "INDISPONÍVEL";
                            System.out.println((i + 1) + ". [" + status + "] " + titulos[i] + " | Autor: " + autores[i] + " | ISBN: " + isbns[i]);
                            System.out.println("   Patrimônio: " + patrimonios[i] + " | Livres: "
                                    + disponiveis[i] + "/" + totaisExemplares[i]
                                    + " | Conservação: " + (bomEstado[i] ? "Adequado" : "Danificado"));
                        }
                        System.out.print("\nDigite 0 para voltar ao menu: ");
                        while (!scanner.hasNextInt() || scanner.nextInt() != 0) {
                            scanner.nextLine();
                            System.out.print("Digite 0 para voltar ao menu: ");
                        }
                        scanner.nextLine();
                        break;

                    default:
                        System.out.println("Opção inválida.");
                }
            }
        }

        scanner.close();
    }
}
