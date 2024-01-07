Em uma imobiliária é necessário controlar o cadastro dos clientes que podem ser pessoas físicas ou pessoas jurídicas que estão interessados em alugar um imóvel.

Os clientes da imobiliária possuem um nome, um telefone (com DDD) e um e-mail de contato. Os clientes do tipo pessoa física possuem um cpf; clientes do tipo pessoa jurídica possuem um cnpj.

Os imóveis que estão disponíveis podem ser comerciais ou residenciais e estão localizados em um endereço no Brasil. Cada imóvel tem um nome e seu próprio valor mensal de aluguel.

Quando um cliente deseja alugar um imóvel, é necessário estabelecer um contrato de aluguel. Em um contrato de aluguel, além do imovel a ser alugado e o cliente que irá alugar, é necessário definir a data de início e a data de término do contrato. Um cliente e um imóvel pode ter vários contratos de aluguéis. No entanto, um imóvel não pode estar em mais de um contrato que tenham períodos conflitantes (datas sobrepostas).

Cliente com 3 ou mais contratos tem 5% de desconto no valor total a ser pago. Cliente com 5 ou mais contratos tem 10% de desconto no valor total a ser pago.

O diagrama de classes a seguir modela parcialmente o sistema:

![image](https://github.com/lucca-software-infnet/Sistema-imobiliario/assets/123994038/791f3f10-070d-4e7b-9bd0-02cbf155c038)


Agora, faça:

A enumeração TipoImovelEnum;
A classe Endereco. Os atributos dessa classe precisam encapsulados e não podem ser nulos ou vazios;
A classe Imovel. Todos os atributos precisam ser encapsulados. Os atributos registro e nome não podem ser nulos ou vazios. Os atributos endereco e tipo não podem ser nulos. O atributo valorAluguel não pode ser menor do que zero. O atributo listaContratos também precisa ser encapsulado, mas não requer validação.
A classe Cliente. Os atributos nome, telefone e email dessa classe precisam encapsulados e não podem ser nulos ou vazios. O atributo listaContratos também precisa ser encapsulado, mas não requer validação.
A classe Cliente deve implementar a Interface Contabil. O método calcularValorTotalContratos() deve realizar o somatorio de todos os valores dos contratos não vencidos que o cliente está associado. Lembre-se da regra de negócio que considera um desconto de 5% ou 10% considerando o número de contratos.
A classe Fisica e a classe Juridica. Os atributos dessas classes precisam ser encapsulados e não podem ser nulos ou vazios.
A classe ContratoAluguel. Importante: não criar métodos setters para essa classe. Crie um contrutor para receber todos os parâmetros (imóvel, cliente, data de início e data de término). Os atributos imovel e cliente não podem ser nulos. Os atributos dataInicio e dataTermino não podem ser nulos e devem ter valores corretos. A data de término não pode ser inferior a data de início.
Na classe ContratoAluguel implementar o método calcularValorContrato() que retorna o valor total do contrato. Ainda nessa classe, implementar o método contratoVencido() que retorna verdadeiro caso a data atual seja maior que a data de término. O método retorna falso caso contrário;
Criar um método toString() na classe ContratoAluguel que formata uma string no formato CSV (comma separated value), com as seguintes informações: o registro do imovel, nome do imovel, endereco do imovel, valor do aluguel mensal do imovel, nome do cliente e telefone do cliente, data de início do contrato, data de término do contrato e a indicação se o contrato está vencido ou não;
Crie classes de exceção para validação das regras relativas à classe Imovel;
Crie classes de exceção para validação das regras relativas à classe Cliente;
Crie classes de exceção para validação das regras relativas à classe ContratoAluguel;
Criar uma classe Main com três listas: uma lista para os imóveis cadastrados; uma para os clientes cadastrados; e uma para os contratos de aluguéis;
O programa deve fornecer um menu com diversas opções. Deve existir uma opção para o usuário terminar a execução do programa;
O sistema deve oferecer uma opção para adicionar um cliente. O sistema deve solicitar ao usuário entrar com os dados;
O sistema deve oferecer uma opção para adicionar um imóvel. O sistema deve solicitar ao usuário entrar com os dados;
O sistema deve oferecer uma opção para adicionar um contrato de aluguel. O sistema deve solicitar ao usuário entrar com os dados. Informe o cpf ou cnpj e o registro do imóvel.
O sistema deve oferecer uma opção para buscar um cliente pelo cpf ou pelo cnpj, apresentar os contratos de aluguéis (número de registro do imóvel, nome do imóvel, data de início e data de término, valor do contrato) e o valor total a ser pago por todos os contratos desse cliente;
O sistema deve oferecer uma opção para buscar um imóvel por um número de registro e apresentar quantos contratos vigentes existem;
O sistema deve oferecer uma opção para exportar todos os contratos de aluguel em formato texto no padrão CSV (comma separated value). Utilizar o método toString() da classe ContratoAluguel;
