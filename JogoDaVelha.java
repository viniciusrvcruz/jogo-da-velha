import java.util.Scanner;
import java.util.Random;

/*
    @author Vinicius Roberto
 */
public class JogoDaVelha {
    
	public char[] adicionarPosicao(boolean randomJogador, int numeroJogador, char[] posicao, char letraJogador ){
    
        int cont = 0, escolha = 0;
        Random random = new Random();
        Scanner ler = new Scanner(System.in);
        
        do {
            if(randomJogador == false) {
                System.out.print("\n[" + numeroJogador + "] - Digite um valor: ");
                escolha = ler.nextInt();
            } else {
                escolha = random.nextInt(10);
            }
            
            
    		if (escolha < 9 && escolha > -1) {
                            
                if (posicao[escolha] == '_' || posicao[escolha] == ' ') {
                    posicao[escolha] = letraJogador;
                    cont = 1;   
                }
    	    }
    	    if(randomJogador == false && cont == 0) {
    	        System.out.println("\nValor inválido ou já existente!\n");
    	    }
        } while (cont == 0);
        
        return posicao;
	}
    
    public boolean verGanhador( int numJogador, char[] posicao, char letraJogador) {

        int i = 0, cont = 0;
        boolean ganhou = false;
        
        System.out.println("");                
        for (i = 0; i < 9; i++) {
            System.out.println(posicao[i] + "|" + posicao[i + 1] + "|" + posicao[i + 2]);
            i = i + 2;
        }
        
        if(ganhou == false) {
            for(cont = 0; cont < 9; cont++) {
                if(posicao[cont] == letraJogador && posicao[cont + 1] == letraJogador && posicao[cont + 2] == letraJogador) {
                    System.out.print("\nJogador " + numJogador + " Ganhou!");
                    ganhou = true;
                }
                
                cont = cont + 2;
            }
        } 
        
        if(ganhou == false) {
            for(cont = 0; cont < 3; cont++) {
         
                if(posicao[cont] == letraJogador && posicao[cont + 3] == letraJogador && posicao[cont + 6] == letraJogador) {
                    System.out.print("\nJogador " + numJogador + " Ganhou!");
                    ganhou = true;
                }
            }
        } 
        
        if(ganhou == false) {
            if(posicao[0] == letraJogador && posicao[4] == letraJogador && posicao[8] == letraJogador || posicao[2] == letraJogador && posicao[4] == letraJogador && posicao[6] == letraJogador) {
                System.out.print("\nJogador " + numJogador + " Ganhou!");
                ganhou = true;
            }
        } 
        
        return ganhou;
    }
	
    public static void main(String[] args) {
        int cont = 0, opcao = 0, i = 0, jogarDeNovo = 0;
        char posicao[] = new char[9];
        boolean randomJogador = false;

        Scanner ler = new Scanner(System.in);
        JogoDaVelha e = new JogoDaVelha();

        do {
            for (i = 0; i < 9; i++) {
            
                if(i < 6) {
                    posicao[i] = '_';
                } else {
                    posicao[i] = ' ';
                }
            }
            
            do {
                System.out.println("\nTecle [1] - 1 player");
                System.out.println("Tecle [2] - 2 players");
                System.out.print("Digite sua escolha: ");
                opcao = ler.nextInt();
                if(opcao == 1 || opcao == 2) {
                    cont = 1;
                } else {
                    System.out.println("\nValor inválido, digite 1 ou 2!\n");
                }
            } while(cont == 0);
            
            System.out.println("\nValores de cada posição:");
            for (i = 0; i < 9; i++) {
                System.out.println(i + "|" + (i + 1) + "|" + (i + 2));
                i = i + 2;
            }
            
            
            do {
                for (i = 0; i < 9; i++) {
                    switch (opcao) {
                        case 1:
                            if (i % 2 == 0) {
                                posicao = e.adicionarPosicao(randomJogador, 1, posicao, 'x');
            
                                if (e.verGanhador(1, posicao, 'x')) {
                                    i = 10;
                                }
                            } else {
                                randomJogador = true;
            
                                posicao = e.adicionarPosicao(randomJogador, 2, posicao, 'o');
            
                                if (e.verGanhador(2, posicao, 'o')) {
                                    i = 10;
                                }
                                randomJogador = false;
                            }
                            break;
                        case 2:
                            if (i % 2 == 0) {
                                posicao = e.adicionarPosicao(randomJogador, 1, posicao, 'x');
            
                                if (e.verGanhador(1, posicao, 'x')) {
                                    i = 10;
                                }
                            } else {
                                posicao = e.adicionarPosicao(randomJogador, 2, posicao, 'o');
            
                                if (e.verGanhador(2, posicao, 'o')) {
                                    i = 10;
                                }
                            }
                            break;
                    }
                }
            } while (i < 9);
            if(i == 9) {
                System.out.println("\nEmpate!\n");
            }
            
            do {
                cont = 0;
                System.out.println("\n\nDeseja jogar de novo?");
                System.out.println("\nDigite [0] - Sim!");
                System.out.println("\nDigite [1] - Não!");
                System.out.print("Resposta: ");
                jogarDeNovo = ler.nextInt();
                if(jogarDeNovo != 0 && jogarDeNovo != 1) {
                    System.out.print("\nValor inválido, digite 0 ou 1!");
                }
            } while (jogarDeNovo != 0 && jogarDeNovo != 1);
        } while (jogarDeNovo == 0);
    }
}