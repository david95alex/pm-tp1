import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;

public class Tabuleiro{

	private Imovel[] tabuleiro;

	public Tabuleiro(String nomeArquivo) throws Exception{
		//variaveis auxiliares na leitura do arquivo
		String linha;
		String[] pedacoLinha;

		FileReader arqTab = new FileReader(nomeArquivo);
		BufferedReader leitorTab = new BufferedReader(arqTab);
		
		//numero de posicoes no tabuleiro
		int nPosicoes = Integer.parseInt(leitorTab.readLine());

		int posicao, tipoPosicao, tipoImovel;
		double valorImovel, taxaImovel;

		tabuleiro = new Imovel[nPosicoes];

		//criacao do tabuleiro
		for(int i=0; i<nPosicoes; i++){
			linha = leitorTab.readLine();
			pedacoLinha = linha.split(";");	

			posicao = Integer.parseInt(pedacoLinha[1]) - 1;
			tipoPosicao = Integer.parseInt(pedacoLinha[2]);
			
			//se a posicao for "inicio", ou "passa a vez", alguns dados nao serao necessarios
			if(tipoPosicao == 1 || tipoPosicao == 2){
			 	tabuleiro[posicao] = new Imovel(tipoPosicao, 0, 0, 0);
			}

			//se for um imovel
			else{
			 	tipoImovel = Integer.parseInt(pedacoLinha[3]);
			 	valorImovel = Double.parseDouble(pedacoLinha[4]);
			 	taxaImovel = Double.parseDouble(pedacoLinha[5]);

			 	tabuleiro[posicao] = new Imovel(tipoPosicao, tipoImovel, valorImovel, taxaImovel);
			}
		}

		leitorTab.close();
	}

	public Imovel[] getTabuleiro(){
		return this.tabuleiro;
	}
}