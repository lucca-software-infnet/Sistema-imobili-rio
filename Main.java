import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.io.FileWriter;
import java.io.IOException;


class ImovelValidationException extends RuntimeException {
    public ImovelValidationException(String message) {
        super(message);
    }
}

class ClienteValidationException extends RuntimeException {
    public ClienteValidationException(String message) {
        super(message);
    }
}

class ContratoAluguelValidationException extends RuntimeException {
    public ContratoAluguelValidationException(String message) {
        super(message);
    }
}

enum TipoImovelEnum {
    COMERCIAL("Comercial"),
    RESIDENCIAL("Residencial");

    private final String descricao;

    private TipoImovelEnum(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}

class Endereco {
    private String logradouro;
    private String tipoLogradouro;
    private String numero;
    private String cidade;
    private String estado;
    private String cep;
    private String complemento;

    public Endereco(String logradouro, String tipoLogradouro, String numero, String complemento, String cidade, String estado, String cep) {
        if (logradouro == null || tipoLogradouro == null || numero == null ||
                cidade == null || estado == null || cep == null || complemento == null|| complemento.isEmpty() ||
                logradouro.isEmpty() || tipoLogradouro.isEmpty() || numero.isEmpty() ||
                cidade.isEmpty() || estado.isEmpty() || cep.isEmpty()) {
            throw new ImovelValidationException("Nenhum dos atributos do endereço pode ser nulo ou vazio");
        }

       
      

        this.logradouro = logradouro;
        this.tipoLogradouro = tipoLogradouro;
        this.numero = numero;
        this.cidade = cidade;
        this.estado = estado;
        this.cep = cep;
        this.complemento = complemento ;
    }


    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getTipoLogradouro() {
        return tipoLogradouro;
    }

    public void setTipoLogradouro(String tipoLogradouro) {
        this.tipoLogradouro = tipoLogradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

   public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }
}

class Imovel {
    private String registro;
    private String nome;
    private Endereco endereco;
    private TipoImovelEnum tipo;
    private float valorAluguel;
    private List<ContratoAluguel> listaContratos;

    public Imovel(String registro, String nome, Endereco endereco, TipoImovelEnum tipo,
                  float valorAluguel ) {
        if (registro == null || endereco == null || tipo == null || nome == null || registro.isEmpty() || nome.isEmpty()) {
            throw new ImovelValidationException("Nenhum dos atributos do imóvel pode ser nulo ou vazio");
        }

        if (endereco == null || tipo == null) {
            throw new ImovelValidationException("O endereço e o tipo do imóvel não podem ser nulos");
        }

        if (valorAluguel < 0) {
            throw new ImovelValidationException("O valor do aluguel não pode ser menor que zero");
        }

        this.registro = registro;
        this.nome = nome;
        this.endereco = endereco;
        this.tipo = tipo;
        this.valorAluguel = valorAluguel;
    }

    public String getRegistro() {
        return registro;
    }

    public void setRegistro(String registro) {
        this.registro = registro;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
   public Endereco getEndereco(){
     return endereco;
   }
  public float getValorAluguel(){
    return valorAluguel;
  }


    public void setListaContratos(List<ContratoAluguel> listaContratos) {
        this.listaContratos = listaContratos;
    }

    public List<ContratoAluguel> getListaContratos() {
        return listaContratos;
    }
}


class ContratoAluguel {
    private Imovel imovel;
    private Cliente cliente;
    private final LocalDate dataInicio;
    private final LocalDate dataTermino;

    public ContratoAluguel(Imovel imovel, Cliente cliente, LocalDate dataInicio, LocalDate dataTermino) {
        if (imovel == null || cliente == null || dataInicio == null || dataTermino == null) {
            throw new ContratoAluguelValidationException(
                    "Imóvel, cliente, data de início e data de término não podem ser nulos.");
        }

        if (dataTermino.isBefore(dataInicio)) {
            throw new ContratoAluguelValidationException("A data de término não pode ser anterior à data de início.");
        }

        this.imovel = imovel;
        this.cliente = cliente;
        this.dataInicio = dataInicio;
        this.dataTermino = dataTermino;
    }

    public Imovel getImovel() {
        return imovel;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public LocalDate getDataTermino() {
        return dataTermino;
    }

  public String getDataInicioFormatada() {
    DateTimeFormatter formatoBrasileiro = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    return dataInicio.format(formatoBrasileiro);
}

public String getDataTerminoFormatada() {
    DateTimeFormatter formatoBrasileiro = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    return dataTermino.format(formatoBrasileiro);
}

    public float calcularValorContrato() {
      float valorTotalContrato = imovel.getValorAluguel() * 12 ;
        return  valorTotalContrato ;
    }

   public boolean contratoVencido() {
    if (LocalDate.now().isBefore(getDataTermino())) {
        return false;
    } else {
        return true;
    }
}

  public String toString() {
    DateTimeFormatter formatoBrasileiro = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    String vencido = contratoVencido() ? "Sim" : "Não";
    return imovel.getRegistro() + "," +
           imovel.getNome() + "," +
           imovel.getEndereco().getLogradouro() + "," +
           imovel.getValorAluguel() + "," +
           cliente.getNome() + "," +
           cliente.getTelefone() + "," +
           dataInicio.format(formatoBrasileiro) + "," +
           dataTermino.format(formatoBrasileiro) + "," +
           vencido;
  }
}

interface Contabil {
    public float calcularValorTotalContrato();
}

abstract class Cliente implements Contabil {
    private String nome;
    private String telefone;
    private String email;
    private List<ContratoAluguel> listaContratos;

    public Cliente(String nome, String telefone, String email, List<ContratoAluguel> listaContratos) {
        if (nome == null || telefone == null || email == null ||
            nome.isEmpty() || telefone.isEmpty() || email.isEmpty()) {
            throw new ClienteValidationException("Nenhum dos atributos do cliente pode ser nulo ou vazio");
        }
       String regex = "^\\(\\d{2}\\)\\d{4}-\\d{4}$";
        Pattern pattern = Pattern.compile(regex);
Matcher matcher = pattern.matcher(telefone);
        if (!matcher.matches()) {
        throw new ImovelValidationException("Formato do número de telefone com DDD incorreto");
    }

        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.listaContratos = listaContratos;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<ContratoAluguel> getListaContratos() {
        return listaContratos;
    }

    public void setListaContratos(List<ContratoAluguel> listaContratos) {
        this.listaContratos = listaContratos;
    }

  public float calcularValorTotalContrato() {
    float totalValue = 0.0f;
    int numContratos = getListaContratos().size();

    for (ContratoAluguel contrato : getListaContratos()) {
        if (!contrato.contratoVencido()) {
            float valorAluguel = contrato.getImovel().getValorAluguel();

            if (numContratos >= 3 && numContratos < 5) {
                totalValue += valorAluguel - (valorAluguel * 0.05f);
            } else if (numContratos >= 5) {
                totalValue += valorAluguel - (valorAluguel * 0.10f);
            } else {
                totalValue += valorAluguel;
            }
        }
    }

    return totalValue;
}


}

class Fisico extends Cliente {
    private String cpf;

    public Fisico(String nome, String telefone, String email, List<ContratoAluguel> listaContrato, String cpf) {
        super(nome, telefone, email, listaContrato);

        if (cpf == null || cpf.isEmpty()) {
            throw new ClienteValidationException("O CPF do cliente não pode ser nulo ou vazio");
        }

        this.cpf = cpf;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    public float calcularValorTotalContrato() {
    float totalValue = 0.0f;
    int numContratos = getListaContratos().size();

    for (ContratoAluguel contrato : getListaContratos()) {
        if (!contrato.contratoVencido()) {
            float valorAluguel = contrato.getImovel().getValorAluguel();

            if (numContratos >= 3 && numContratos < 5) {
                totalValue += valorAluguel - (valorAluguel * 0.05f);
            } else if (numContratos >= 5) {
                totalValue += valorAluguel - (valorAluguel * 0.10f);
            } else {
                totalValue += valorAluguel;
            }
        }
    }

    return totalValue;
}

}

class Juridico extends Cliente {
    private String cnpj;

    public Juridico(String nome, String telefone, String email, List<ContratoAluguel> listaContrato, String cnpj) {
        super(nome, telefone, email, listaContrato);

        if (cnpj == null || cnpj.isEmpty()) {
            throw new ClienteValidationException("O CNPJ do cliente não pode ser nulo ou vazio");
        }

        this.cnpj = cnpj;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
    public float calcularValorTotalContrato() {
    float totalValue = 0.0f;
    int numContratos = getListaContratos().size();

    for (ContratoAluguel contrato : getListaContratos()) {
        if (!contrato.contratoVencido()) {
            float valorAluguel = contrato.getImovel().getValorAluguel();

            if (numContratos >= 3 && numContratos < 5) {
                totalValue += valorAluguel - (valorAluguel * 0.05f);
            } else if (numContratos >= 5) {
                totalValue += valorAluguel - (valorAluguel * 0.10f);
            } else {
                totalValue += valorAluguel;
            }
        }
    }

    return totalValue;
}

}

 class Main {
    public static void main(String[] args) {
        List<Imovel> imoveis = new ArrayList<>();
        List<Cliente> clientes = new ArrayList<>();
        List<ContratoAluguel> contratos = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("===== Menu =====");
            System.out.println("1. Adicionar Cliente");
            System.out.println("2. Adicionar Imóvel");
            System.out.println("3. Adicionar Contrato de Aluguel");
            System.out.println("4. Buscar Cliente e seus Contratos");
            System.out.println("5. Buscar Imóvel e seus Contratos Vigentes");
            System.out.println("6. Exportar Contratos em CSV");
            System.out.println("7. Sair");

            int opcao = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcao) {
                case 1:
                    adicionarCliente(clientes, scanner);
                    break;
                case 2:
                    adicionarImovel(imoveis, scanner);
                    break;
                case 3:
                    adicionarContratoAluguel(contratos, imoveis, clientes, scanner);
                    break;
                case 4:
                    buscarClienteEContratos(clientes, contratos, scanner);
                    break;
                case 5:
                    buscarImovelEContratosVigentes(imoveis, contratos, scanner);
                    break;
                case 6:
                    exportarContratosCSV(contratos);
                    break;
                case 7:
                    System.out.println("Encerrando o programa...");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private static void adicionarCliente(List<Cliente> clientes, Scanner scanner) {
        System.out.print("Nome do Cliente: ");
        String nome = scanner.nextLine();
        System.out.print("Telefone do Cliente no formato (XX) XXXXX-XXXX: ");
        String telefone = scanner.nextLine();
        System.out.print("Email do Cliente: ");
        String email = scanner.nextLine();
        System.out.print("CPF ou CNPJ do Cliente: ");
        String cpfCnpj = scanner.nextLine();

        List<ContratoAluguel> listaContrato = new ArrayList<>();
        Cliente cliente;
        if (cpfCnpj.length() == 11) {
            cliente = new Fisico(nome, telefone, email, listaContrato, cpfCnpj);
        } else if (cpfCnpj.length() == 14) {
            cliente = new Juridico(nome, telefone, email, listaContrato, cpfCnpj);
        } else {
            System.out.println("CPF ou CNPJ inválido.");
            return;
        }

        clientes.add(cliente);
        System.out.println("Cliente adicionado com sucesso!");
    }

   private static void adicionarImovel(List<Imovel> imoveis, Scanner scanner) {
    System.out.print("Registro do Imóvel: ");
    String registro = scanner.nextLine();
    System.out.print("Nome do Imóvel: ");
    String nome = scanner.nextLine();
    System.out.print("Logradouro: ");
    String logradouro = scanner.nextLine();
    System.out.print("Tipo do Logradouro: ");
    String tipoLogradouro = scanner.nextLine();
    System.out.print("Número :");
    String numero = scanner.nextLine();
    System.out.print("Complemento: ");
    String complemento = scanner.nextLine();
    System.out.print("Cidade: ");
    String cidade = scanner.nextLine();
    System.out.print("Estado: ");
    String estado = scanner.nextLine();
    System.out.print("CEP: ");
    String cep = scanner.nextLine();
    System.out.print("Tipo de Imóvel (COMERCIAL ou RESIDENCIAL): ");
    String tipoStr = scanner.nextLine();
    System.out.print("Valor do Aluguel Mensal: ");
    float valorAluguel = scanner.nextFloat();
   
  
     
    TipoImovelEnum tipo;
    try {
        tipo = TipoImovelEnum.valueOf(tipoStr.toUpperCase());
    } catch (IllegalArgumentException e) {
        System.out.println("Tipo de Imóvel inválido.");
        return;
    }

    Endereco endereco = new Endereco(logradouro, tipoLogradouro, numero, complemento, cidade, estado, cep);
        Imovel imovel = new Imovel(registro, nome, endereco, tipo, valorAluguel); 

     
     imoveis.add(imovel);
    System.out.println("Imóvel adicionado com sucesso!");
}


    private static void adicionarContratoAluguel(
        List<ContratoAluguel> contratos, List<Imovel> imoveis, List<Cliente> clientes, Scanner scanner) {
    System.out.print("CPF ou CNPJ do Cliente: ");
    String cpfCnpj = scanner.nextLine();
    System.out.print("Registro do Imóvel: ");
    String registroImovel = scanner.nextLine();

    Cliente cliente = buscarClientePorCpfCnpj(clientes, cpfCnpj);
    Imovel imovel = buscarImovelPorRegistro(imoveis, registroImovel);

    if (cliente == null || imovel == null) {
        System.out.println("Cliente ou Imóvel não encontrado.");
        return;
    }

    System.out.print("Data de Início do Contrato (dd/MM/yyyy): ");
String dataInicioStr = scanner.nextLine();
System.out.print("Data de Término do Contrato (dd/MM/yyyy): ");
String dataTerminoStr = scanner.nextLine();

DateTimeFormatter formatoBrasileiro = DateTimeFormatter.ofPattern("dd/MM/yyyy");
LocalDate dataInicio = LocalDate.parse(dataInicioStr, formatoBrasileiro);
LocalDate dataTermino = LocalDate.parse(dataTerminoStr, formatoBrasileiro);

ContratoAluguel contrato = new ContratoAluguel(imovel, cliente, dataInicio, dataTermino);
    
contratos.add(contrato); 
    System.out.println("Contrato de Aluguel adicionado com sucesso!");
}


    private static Cliente buscarClientePorCpfCnpj(List<Cliente> clientes, String cpfCnpj) {
        for (Cliente cliente : clientes) {
            if (cliente instanceof Fisico) {
                if (((Fisico) cliente).getCpf().equals(cpfCnpj)) {
                    return cliente;
                }
            } else if (cliente instanceof Juridico) {
                if (((Juridico) cliente).getCnpj().equals(cpfCnpj)) {
                    return cliente;
                }
            }
        }
        return null;
    }

    private static Imovel buscarImovelPorRegistro(List<Imovel> imoveis, String registro) {
        for (Imovel imovel : imoveis) {
            if (imovel.getRegistro().equals(registro)) {
                return imovel;
            }
        }
        return null;
    }

    private static void buscarClienteEContratos(List<Cliente> clientes, List<ContratoAluguel> contratos, Scanner scanner) {
        System.out.print("CPF ou CNPJ do Cliente: ");
        String cpfCnpj = scanner.nextLine();

        Cliente cliente = buscarClientePorCpfCnpj(clientes, cpfCnpj);

        if (cliente == null) {
            System.out.println("Cliente não encontrado.");
            return;
        }

        System.out.println("===== Contratos de Aluguel =====");
        for (ContratoAluguel contrato : contratos) {
            if (contrato.getCliente() == cliente) {
                System.out.println("Registro do Imóvel: " + contrato.getImovel().getRegistro());
                System.out.println("Nome do Imóvel: " + contrato.getImovel().getNome());
                System.out.println("Data de Início: " + contrato.getDataInicioFormatada());
                System.out.println("Data de Término: " + contrato.getDataTerminoFormatada());
                System.out.println("Valor do Contrato: " + contrato.calcularValorContrato());
            }
        }

        float totalContratos = cliente.calcularValorTotalContrato();
        System.out.println("===== Valor Total a Ser Pago por Todos os Contratos: " + totalContratos);
    }

    private static void buscarImovelEContratosVigentes(List<Imovel> imoveis, List<ContratoAluguel> contratos, Scanner scanner) {
        System.out.print("Registro do Imóvel: ");
        String registro = scanner.nextLine();

        Imovel imovel = buscarImovelPorRegistro(imoveis, registro);

        if (imovel == null) {
            System.out.println("Imóvel não encontrado.");
            return;
        }

        int contratosVigentes = 0;
        LocalDateTime dataAtual = LocalDateTime.now();

        for (ContratoAluguel contrato : contratos) {
           if (contrato.getImovel() == imovel && !contrato.contratoVencido() && dataAtual.toLocalDate().isBefore(contrato.getDataTermino())) {
    contratosVigentes++;
}
        }

        System.out.println("===== Contratos Vigentes do Imóvel =====");
        System.out.println("Número de Contratos Vigentes: " + contratosVigentes);
    }

private static void exportarContratosCSV(List<ContratoAluguel> contratos) {
    try {
        FileWriter writer = new FileWriter("contratos.csv");

        writer.write("Registro do Imóvel, Nome do Imóvel, Logradouro, Valor do Aluguel, Nome do Cliente, Telefone do Cliente, Data de Início, Data de Término, Vencido\n");

        for (ContratoAluguel contrato : contratos) {
            writer.write(contrato.toString() + "\n");
        }

        writer.close();
        System.out.println("Arquivo CSV 'contratos.csv' gerado com sucesso.");
    } catch (IOException e) {
        System.err.println("Erro ao criar o arquivo CSV: " + e.getMessage());
    }
}
}