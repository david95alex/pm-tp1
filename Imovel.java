public class Imovel{
	private int tipoPosicao;
	private int tipoImovel;
	private Jogador dono;
	private double valorCompra;
	private double valorAluguel;

	public Imovel(int tipoPosicao, int tipoImovel, double valorCompra, double taxa){
		this.tipoPosicao = tipoPosicao;
		setTipoImovel(tipoImovel);
		restituiPropriedade();
		setValorCompra(valorCompra);
		setValorAluguel(taxa);
	}

	public int getTipoPosicao(){
		return this.tipoPosicao;
	}

	public int getTipoImovel(){
		return this.tipoImovel;
	}

	public void setTipoImovel(int novoTipo){
		this.tipoImovel = novoTipo;
	}

	public Jogador getDono(){
		return this.dono;
	}

	public void setDono(Jogador novoDono){
		this.dono = novoDono;
	}

	public double getValorCompra(){
		return this.valorCompra;
	}

	public void setValorCompra(double novoValorCompra){
		this.valorCompra = novoValorCompra;
	}

	public double getValorAluguel(){
		return this.valorAluguel;
	}

	public void setValorAluguel(double taxa){
		this.valorAluguel = getValorCompra() * (taxa/100);
	}

	//caso um jogador que possuia este imovel saia do jogo, retorna este imovel ao banco
	public void restituiPropriedade(){
		this.dono = null;
	}
}