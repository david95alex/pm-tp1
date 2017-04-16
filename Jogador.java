public class Jogador{
	private int jogadas;
	private boolean jogando = true;
	private int id;
	private double saldo;
	private int posicao;

	//constante que representa dinheiro ganho ao passar pelo start
	private final int bonus = 500;

	//estatisticas a serem impressas na saida
	private int voltas = 0;
	private double aluguelRecebido = 0;
	private double aluguelPago = 0;
	private double imoveisComprados = 0;
	private int passouVez = 0;

	public Jogador(int id, double saldo, int posicao){
		setId(id);
		setSaldo(saldo);
		setPosicao(posicao);
	}

	public int getJogadas(){
		return this.jogadas;
	}

	public boolean getJogando(){
		return this.jogando;
	}

	public void saiDoJogo(Jogando jogando, Imovel[] tabuleiro){
		this.jogando = false;
		jogando.tiraJogador();

		//restitui todas as propriedades desse jogador ao banco
		for(int i=0; i<tabuleiro.length; i++){
			if(tabuleiro[i].getDono() == this){
				tabuleiro[i].restituiPropriedade();
			}
		}
	}

	public int getId(){
		return this.id;
	}
	public void setId(int id){
		this.id = id;
	}

	public double getSaldo(){
		return this.saldo;
	}
	public void setSaldo(double saldo){
		this.saldo = saldo;
	}

	public int getPosicao(){
		return this.posicao;
	}
	public void setPosicao(int posicao){
		this.posicao = posicao;
	}

	public int getVoltas(){
		return this.voltas;
	}

	public double getAluguelRecebido(){
		return this.aluguelRecebido;
	}

	public double getAluguelPago(){
		return this.aluguelPago;
	}

	public double getImoveisComprados(){
		return this.imoveisComprados;
	}

	public int getPassouVez(){
		return this.passouVez;
	}

	public void compraImovel(Imovel imovel){
		this.saldo -= imovel.getValorCompra();
		this.imoveisComprados += imovel.getValorCompra();
		imovel.setDono(this);
	}

	public void pagaAluguel(Imovel imovel){
		this.saldo -= imovel.getValorAluguel();
		this.aluguelPago += imovel.getValorAluguel();
	}

	public void recebeAluguel(Imovel imovel){
		this.saldo += imovel.getValorAluguel();
		this.aluguelRecebido += imovel.getValorAluguel();
	}

	public void completaVolta(){
		this.saldo += bonus;
		this.voltas++;
	}

	public void passaVez(){
		this.passouVez++;
	}

	public void joga(Imovel[] tabuleiro, int dado, Jogando jogando){
		this.jogadas++;

		int novaPosicao;
		novaPosicao = this.posicao + dado;

		//completou uma ou mais voltas (dependendo do tamanho do tabuleiro)
		if(novaPosicao > tabuleiro.length - 1){
			int voltasCompletas = novaPosicao/tabuleiro.length;

			for(int i = 0; i<voltasCompletas; i++){
				this.completaVolta();
			}
			novaPosicao = novaPosicao % tabuleiro.length;
		}

		this.setPosicao(novaPosicao);

		//caiu no "start"
		if(tabuleiro[novaPosicao].getTipoPosicao() == 1)
			return;

		//caiu num "passe a vez"
		else if(tabuleiro[novaPosicao].getTipoPosicao() == 2)
			this.passaVez();
		

		//caiu num imovel que nao eh de ninguem
		else if(tabuleiro[novaPosicao].getDono() == null){

			//se tiver saldo, compra imovel
			if(this.saldo >= tabuleiro[novaPosicao].getValorCompra())
				this.compraImovel(tabuleiro[novaPosicao]);			
		}

		//caiu num imovel que nao eh dele
		else if(tabuleiro[novaPosicao].getDono() != this){ 

			//se tiver saldo, paga aluguel
			if(this.saldo >= tabuleiro[novaPosicao].getValorAluguel()){
				this.pagaAluguel(tabuleiro[novaPosicao]);
				tabuleiro[novaPosicao].getDono().recebeAluguel(tabuleiro[novaPosicao]);
			}

			//se nao, sai do jogo
			else{
				this.saiDoJogo(jogando, tabuleiro);
			}
		}
	}
}