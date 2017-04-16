import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.File;

public class BancoImobiliario{
	public static void main(String[] args) throws Exception{
		//constroi tabuleiro
		Tabuleiro tabuleiro = new Tabuleiro("tabuleiro.txt");

		//faz as jogadas
		Jogadas jogadas = new Jogadas("jogadas.txt", tabuleiro);
	}
}