package util;

/**
 *
 * @author jefferson
 */
public class Verifica {
    
    public boolean Cpf(String cpf){
        
        /*Verfica se nao esta vindo nada em caso de algum erro*/
        if (cpf.equals("   .   .   -  ")){
            return(false);
        }
        
        /*Um cpf com os pontos e o traco tem tamanho 14*/
        if (cpf.length() == 14){
            
            /*Cria um vetor de inteiro que ira guardar os 9 primeiros numeros do cpf*/
            int digitos[] = new int[10];
            
            /*Cria um vetor de pesos para auxiliar na parte do digito verificador*/
            int pesos[]   = new int[10];
            
            /*Variavel string para auxiliar na parte de conferir se os numeros sao iguais*/
            String iguais = "";
            
            /*Variavel para auxiliar em qual posicao do vetor colocar os valores*/            
            int auxilia = 0;
            
            /*Loop indo de 0 a 10, ou seja, ele ira trabalhar so com os 9 primeiros numeros do cpf e os 2 pontos*/
            for (int i = 0; i < (cpf.length() - 3); i++) {
                
                /*Nessas posicoes estao os pontos e o traco, portanto nao faz nada*/
                if ((i == 3) || (i == 7)){
                    continue;
                }
                
                digitos[auxilia]   = Integer.parseInt(String.valueOf(cpf.charAt(i)));                
                pesos[auxilia + 1] = (pesos.length - auxilia);
                
                auxilia++;
            }
            
            /*Os mesmos numeros em sequencias burlam o calculo do digito verificador*/
            for (int i = 0; i < (digitos.length - 1); i++) {
                iguais += digitos[i];                
            }
            
            if (iguais.equals("111111111") ||
                iguais.equals("222222222") ||
                iguais.equals("333333333") ||
                iguais.equals("444444444") ||
                iguais.equals("555555555") ||
                iguais.equals("666666666") ||
                iguais.equals("777777777") ||
                iguais.equals("888888888") ||
                iguais.equals("999999999")){
                return false;
            }
            
            /*Variavel auxiliar para guardar a soma da multiplicao dos digitos pelos pesos*/
            int soma = 0;            
            
            /**********************
             * Digito verificador *
             **********************/
            
            /*Primeiro digito verificador*/
            for (int i = 0; i < (digitos.length - 1); i++) {
                soma += (digitos[i] * pesos[i + 1]);
            }
            
            /*
              O resultado obtido sera divido por 11. Considere como quociente apenas o valor inteiro obtido na divisao, 
              o resto da divisao sera responsavel pelo calculo do primeiro digito verificador.
              Se o resultado da subtracao for maior que 9, o digito verificador e ZERO. 
              Caso contrario, o digito verificador e o resultado dessa subtracao.
            */
            int restoDivisao = (soma % 11);
            int aux = 11 - restoDivisao;
            int primeiroVerificador = 0;
            if (!(aux > 9)){
                primeiroVerificador = aux;
            }
            
            /*
              Para calcular o segundo digito verificador, fazemos o calculo de forma analoga ao primeiro digito, 
              acrescentando ao CPF o digito encontrado no passo anterior.
              Na segunda linha, os pesos sao distribuidos comecando por 11.
            */
            soma     = 0; 
            pesos[0] = 11;
            digitos[digitos.length - 1] = primeiroVerificador;
            
            /*Segundo digito verificador*/
            for (int i = 0; i < digitos.length; i++) {
                soma += (digitos[i] * pesos[i]);
            }
            
            /*Para calcular o digito verificador, voce deve subtrair o resto encontrado de onze.
              Como agora o resultado da subtracao nao e maior que 9, o resultado e o proprio digito verificador.
            */
            restoDivisao = (soma % 11);
            int segundoVerificador = (11 - restoDivisao);
            
            /*Verificando no cpf informado se os dois digitos correspondem ao calculos*/
            int cpfPenultimoDigito = Integer.parseInt(String.valueOf(cpf.charAt(cpf.length() - 2)));
            int cpfUltimoDigito    = Integer.parseInt(String.valueOf(cpf.charAt(cpf.length() - 1)));
            
            /*Se os valores sao iguais o cpf e aceito*/
            if ((cpfPenultimoDigito == primeiroVerificador) &&
                (cpfUltimoDigito    == segundoVerificador)){
                return true;
            }
            
        }
        
        /*Houve alguma inconsistencia*/
        return(false);
    }    
}
