//classe que controla o numero de jogadores que ainda estao jogando
public class Jogando{
	private int nJogando;

	public Jogando(int nJogando){
		this.nJogando = nJogando;
	}

	public int getJogando(){
		return this.nJogando;
	}

	public void tiraJogador(){
		this.nJogando--;
	}
}