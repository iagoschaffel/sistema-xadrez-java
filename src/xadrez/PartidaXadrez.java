package xadrez;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import jogotabuleiro.Pe�a;
import jogotabuleiro.Posicao;
import jogotabuleiro.Tabuleiro;
import xadrez.pe�as.Bispo;
import xadrez.pe�as.Cavalo;
import xadrez.pe�as.Peao;
import xadrez.pe�as.Rainha;
import xadrez.pe�as.Rei;
import xadrez.pe�as.Torre;

public class PartidaXadrez {
	
	private int turno;
	private Cor Jogadoratual;
	private Tabuleiro tabuleiro;
	private boolean xeque;
	private boolean xequeMate;
	
	private List<Pe�a> pe�asNoTabuleiro = new ArrayList<>();
	private List<Pe�a> pe�asCapturadas = new ArrayList<>();
	
	public PartidaXadrez() {
		tabuleiro = new Tabuleiro(8,8);
		turno = 1;
		Jogadoratual = Cor.BRANCO;
		Configuracaoinicial();
	}
	
	public int getTurno() {
		return turno;
	}
	
	public Cor getJogadorAtual() {
		return Jogadoratual;
	}
	
	public boolean getXeque() {
		return xeque;
	}
	
	public boolean getXequeMate() {
		return xequeMate;
	}
	
	public Pe�aXadrez[][] getPe�as() {
		
		Pe�aXadrez[][] mat = new Pe�aXadrez[tabuleiro.getLinhas()][tabuleiro.getColunas()];
		for(int i=0;i<tabuleiro.getLinhas();i++) {
			for(int j=0;j<tabuleiro.getColunas();j++) {
				mat[i][j] = (Pe�aXadrez) tabuleiro.pe�a(i,j);
			}
		}
		return mat;
	}
	
	public boolean[][] Movimentospossiveis(PosicaoXadrez origemPosicao){
		Posicao posicao = origemPosicao.paraPosicao();
		validarOrigemPosicao(posicao);
		return tabuleiro.pe�a(posicao).Movimentospossiveis();
	}
	
	public Pe�aXadrez realizarMovimentoXadrez(PosicaoXadrez origemPosicao, PosicaoXadrez alvoPosicao) {
		Posicao origem = origemPosicao.paraPosicao();
		Posicao alvo = alvoPosicao.paraPosicao();
		validarOrigemPosicao(origem);
		validarAlvoPosicao(origem, alvo);
		Pe�a Pe�acapturada = fazerMovimento(origem, alvo);
		
		if(testeXeque(Jogadoratual)) {
			desfazerMovimento(origem, alvo, Pe�acapturada);
			throw new ExcecaoXadrez("Voce nao pode se colocar em xeque.");
		}
		
		xeque = (testeXeque(oponente(Jogadoratual))) ? true : false;
		
		if(testeXequeMate(oponente(Jogadoratual))) {
			xequeMate = true;
		}
		else {
			proximoTurno();
		}
		return (Pe�aXadrez)Pe�acapturada;
	}
	
	private void validarOrigemPosicao(Posicao posicao) {
		if(!tabuleiro.temUmaPe�a(posicao)) {
			throw new ExcecaoXadrez("Nao existe pe�a na posi��o de origem.");
		}
		if(Jogadoratual != ((Pe�aXadrez)tabuleiro.pe�a(posicao)).getCor()){
			throw new ExcecaoXadrez("A pe�a escolhida nao � sua.");
		}
		if(!tabuleiro.pe�a(posicao).ExisteMovimentoPossivel()) {
			throw new ExcecaoXadrez("Nao existem movimentos possiveis para a pe�a escolhida.");
		}
		
	}
	
	private void validarAlvoPosicao(Posicao origem, Posicao alvo) {
		if(!tabuleiro.pe�a(origem).Movimentopossivel(alvo)) {
		throw new ExcecaoXadrez("A pe�a escolhida n�o pode se mover para a posi��o destino.");
		}
	}
	
	private Pe�a fazerMovimento(Posicao origem, Posicao alvo) {
		Pe�aXadrez p = (Pe�aXadrez)tabuleiro.removerPe�a(origem);
		p.incrementarContadorMovimento();
		Pe�a pe�aCapturada = tabuleiro.removerPe�a(alvo);
		tabuleiro.posicionarPe�a(p, alvo);
		
		if(pe�aCapturada != null) {
			pe�asNoTabuleiro.remove(pe�aCapturada);
			pe�asCapturadas.add(pe�aCapturada);
		}
		
		return pe�aCapturada;
		
	}
	
	private void desfazerMovimento(Posicao origem, Posicao alvo, Pe�a pe�aCapturada) {
		Pe�aXadrez p = (Pe�aXadrez)tabuleiro.removerPe�a(alvo);
		p.decrementarContadorMovimento();
		tabuleiro.posicionarPe�a(p,origem);
		
		if(pe�aCapturada != null) {
			tabuleiro.posicionarPe�a(pe�aCapturada, alvo);
			pe�asCapturadas.remove(pe�aCapturada);
			pe�asNoTabuleiro.add(pe�aCapturada);
		}
	}
	
	private void proximoTurno() {
		turno++;
		Jogadoratual = (Jogadoratual == Cor.BRANCO) ? Cor.PRETO : Cor.BRANCO;
	}
	
	private Cor oponente(Cor cor) {
		return(cor == Cor.BRANCO) ? Cor.PRETO : Cor.BRANCO;
	}
	
	private Pe�aXadrez rei(Cor cor) {
		List<Pe�a> lista = pe�asNoTabuleiro.stream().filter(x -> ((Pe�aXadrez)x).getCor() == cor).collect(Collectors.toList());
		for(Pe�a p : lista) {
			if(p instanceof Rei) {
				return (Pe�aXadrez)p;
			}
		}
		throw new IllegalStateException("Nao existe o rei " + cor + " no tabuleiro.");
	}
	
	private boolean testeXeque(Cor cor) {
		Posicao Posicaorei = rei(cor).getPosicaoXadrez().paraPosicao();
		List<Pe�a> Pe�asoponente = pe�asNoTabuleiro.stream().filter(x -> ((Pe�aXadrez)x).getCor() == oponente(cor)).collect(Collectors.toList());
		for(Pe�a p : Pe�asoponente) {
			boolean [][] mat = p.Movimentospossiveis();
			if(mat[Posicaorei.getLinha()][Posicaorei.getColuna()]) {
				return true;
			}
		}
		return false;
	}
	
	private boolean testeXequeMate(Cor cor) {
		if(!testeXeque(cor)) {
			return false;
		}
		List<Pe�a> lista = pe�asNoTabuleiro.stream().filter(x -> ((Pe�aXadrez)x).getCor() == cor).collect(Collectors.toList());
		for(Pe�a p : lista) {
			boolean [][] mat = p.Movimentospossiveis();
			for(int i=0; i<tabuleiro.getLinhas();i++) {
				for(int j=0; j<tabuleiro.getColunas();j++) {
					if(mat[i][j]) {
						Posicao origem = ((Pe�aXadrez)p).getPosicaoXadrez().paraPosicao();
						Posicao alvo = new Posicao(i,j);
						Pe�a Pe�aCapturada = fazerMovimento(origem, alvo);
						boolean testeXeque = testeXeque(cor);
						desfazerMovimento(origem, alvo, Pe�aCapturada);
						if(!testeXeque) {
							return false;
						}
					}
				}
			}
		}
		return true;
	}
	
	private void posicionarPe�aNova(char coluna, int linha, Pe�aXadrez pe�a) {
		tabuleiro.posicionarPe�a(pe�a, new PosicaoXadrez(coluna,linha).paraPosicao());
		pe�asNoTabuleiro.add(pe�a);
	}
	
	private void Configuracaoinicial() {
		posicionarPe�aNova('a', 1, new Torre(tabuleiro, Cor.BRANCO));
		posicionarPe�aNova('h', 1, new Torre(tabuleiro, Cor.BRANCO));
		posicionarPe�aNova('e', 1, new Rei(tabuleiro, Cor.BRANCO));
		posicionarPe�aNova('d', 1, new Rainha(tabuleiro, Cor.BRANCO));
		posicionarPe�aNova('c', 1, new Bispo(tabuleiro, Cor.BRANCO));
		posicionarPe�aNova('f', 1, new Bispo(tabuleiro, Cor.BRANCO));
		posicionarPe�aNova('b', 1, new Cavalo(tabuleiro,Cor.BRANCO));
		posicionarPe�aNova('g', 1, new Cavalo(tabuleiro,Cor.BRANCO));
		posicionarPe�aNova('a', 2, new Peao(tabuleiro, Cor.BRANCO));
		posicionarPe�aNova('b', 2, new Peao(tabuleiro, Cor.BRANCO));
		posicionarPe�aNova('c', 2, new Peao(tabuleiro, Cor.BRANCO));
		posicionarPe�aNova('d', 2, new Peao(tabuleiro, Cor.BRANCO));
		posicionarPe�aNova('e', 2, new Peao(tabuleiro, Cor.BRANCO));
		posicionarPe�aNova('f', 2, new Peao(tabuleiro, Cor.BRANCO));
		posicionarPe�aNova('g', 2, new Peao(tabuleiro, Cor.BRANCO));
		posicionarPe�aNova('h', 2, new Peao(tabuleiro, Cor.BRANCO));

		posicionarPe�aNova('a', 8, new Torre(tabuleiro, Cor.PRETO));
		posicionarPe�aNova('h', 8, new Torre(tabuleiro, Cor.PRETO));
		posicionarPe�aNova('e', 8, new Rei(tabuleiro, Cor.PRETO));
		posicionarPe�aNova('d', 8, new Rainha(tabuleiro, Cor.PRETO));
		posicionarPe�aNova('c', 8, new Bispo(tabuleiro, Cor.PRETO));
		posicionarPe�aNova('f', 8, new Bispo(tabuleiro, Cor.PRETO));
		posicionarPe�aNova('b', 8, new Cavalo(tabuleiro,Cor.PRETO));
		posicionarPe�aNova('g', 8, new Cavalo(tabuleiro,Cor.PRETO));
		posicionarPe�aNova('a', 7, new Peao(tabuleiro, Cor.PRETO));
		posicionarPe�aNova('b', 7, new Peao(tabuleiro, Cor.PRETO));
		posicionarPe�aNova('c', 7, new Peao(tabuleiro, Cor.PRETO));
		posicionarPe�aNova('d', 7, new Peao(tabuleiro, Cor.PRETO));
		posicionarPe�aNova('e', 7, new Peao(tabuleiro, Cor.PRETO));
		posicionarPe�aNova('f', 7, new Peao(tabuleiro, Cor.PRETO));
		posicionarPe�aNova('g', 7, new Peao(tabuleiro, Cor.PRETO));
		posicionarPe�aNova('h', 7, new Peao(tabuleiro, Cor.PRETO));
		
	}

}
