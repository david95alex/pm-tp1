import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.File;

public class Jogadas{

	private Jogando jogando;
	private Jogador[] jogadores;

	public Jogadas(String nomeArquivo, Tabuleiro tabuleiro) throws Exception{
		//variaveis auxiliares para leitura do arquivo
		String linha;
		String[] pedacoLinha;

		FileReader arqJog = new FileReader(nomeArquivo);
		BufferedReader leitorJog = new BufferedReader(arqJog);

		linha = leitorJog.readLine();
		pedacoLinha = linha.split("%");

		int nJogadas = Integer.parseInt(pedacoLinha[0]);
		int nJogadores = Integer.parseInt(pedacoLinha[1]);
		int saldoInicial = Integer.parseInt(pedacoLinha[2]);

		//cria jogadores
		jogando = new Jogando(nJogadores);
		jogadores = new Jogador[nJogadores];

		//inicializa jogadores
		for(int i=0; i < nJogadores; i++){
			jogadores[i] = new Jogador(i+1, saldoInicial, 0);
		}

		int idJog;
		int dado;

		//leitura e execucao das jogadas
		for(int i=0; i<nJogadas-1; i++){
			//se existe apenas um jogador participando, o jogo termina
			if(jogando.getJogando() == 1)
				break;
			
			linha = leitorJog.readLine();
			pedacoLinha = linha.split(";");

			idJog = Integer.parseInt(pedacoLinha[1]);
			dado = Integer.parseInt(pedacoLinha[2]);

			//analisa se o jogador em questao ainda esta em jogo.
			//Caso nao esteja, passa para a proxima jogada.
			if(jogadores[idJog - 1].getJogando() == true){
				jogadores[idJog-1].joga(tabuleiro.getTabuleiro(), dado, jogando);
			}
		}

		leitorJog.close();

		//imprime as estatisticas
		saida(this.jogadores);
	}

	public Jogando getJogando(){
		return this.jogando;
	}

	public Jogador[] getJogadores(){
		return this.jogadores;
	}

	//Esta funcao transforma um double cuja mantissa eh nula em inteiro.
	//Se a mantissa nao for inteira, formata o valor para 2 casas de precisao
	public static String converteDouble(double numero){
		if((int)numero < numero){
			return String.format("%.2f",numero);
		}

		else{
			return String.format("%.0f",numero);
		}
	}

	//imprime saida
	public static void saida(Jogador [] jogadores) throws Exception{

		File estatisticas = new File("estatiscas.txt");
		FileWriter saida = new FileWriter(estatisticas);
		BufferedWriter escritor = new BufferedWriter(saida);

		//pergunta 1: numero de rodadas
		escritor.write("1:");

		int rodadas = 0;
		for(int i=0; i < jogadores.length; i++){
			if(jogadores[i].getJogadas() >= rodadas)
				rodadas = jogadores[i].getJogadas();
		}

		escritor.write(rodadas + "\n");

		//pergunta 2: numero de voltas de cada jogador
		escritor.write("2:");
		for(int i=0; i < jogadores.length; i++){

			escritor.write((i+1) + "-" + jogadores[i].getVoltas());

			if(i != jogadores.length -1){
				escritor.write(";");				
			}

			else{
				escritor.write("\n");
			}
		}

		//pergunta 3: saldo de cada jogador
		escritor.write("3:");
		for(int i=0; i < jogadores.length; i++){

			escritor.write((i+1) + "-" + converteDouble(jogadores[i].getSaldo()));

			if(i != jogadores.length -1){
				escritor.write(";");				
			}
			else{
				escritor.write("\n");
			}
		}

		//pergunta 4: total de alugueis recebidos por cada jogador
		escritor.write("4:");
		for(int i=0; i < jogadores.length; i++){

			escritor.write((i+1) + "-" + converteDouble(jogadores[i].getAluguelRecebido()));

			if(i != jogadores.length -1){
				escritor.write(";");				
			}

			else{
				escritor.write("\n");
			}
		}

		//pergunta 5: total de alugueis pagos por cada jogador
		escritor.write("5:");
		for(int i=0; i < jogadores.length; i++){

			escritor.write((i+1) + "-" + converteDouble(jogadores[i].getAluguelPago()));

			if(i != jogadores.length -1){
				escritor.write(";");				
			}

			else{
				escritor.write("\n");
			}
		}

		//pergunta 6: total do valor gasto na compra de imoveis por cada jogador
		escritor.write("6:");
		for(int i=0; i < jogadores.length; i++){

			escritor.write((i+1) + "-" + converteDouble(jogadores[i].getImoveisComprados()));

			if(i != jogadores.length -1){
				escritor.write(";");				
			}

			else{
				escritor.write("\n");
			}
		}

		//pergunta 7: numero de vezes que cada jogador passou a vez
		escritor.write("7:");
		for(int i=0; i < jogadores.length; i++){

			escritor.write((i+1) + "-" + jogadores[i].getPassouVez());

			if(i != jogadores.length -1){
				escritor.write(";");				
			}

			else{
				escritor.write("\n");
			}
		}

		escritor.close();
	}
}