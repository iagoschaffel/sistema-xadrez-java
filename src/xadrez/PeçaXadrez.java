package xadrez;

import jogotabuleiro.Pe�a;
import jogotabuleiro.Posicao;
import jogotabuleiro.Tabuleiro;

public abstract class Pe�aXadrez extends Pe�a {

		private Cor cor;
		private int Contadormovimento;

		public Pe�aXadrez(Tabuleiro tabuleiro, Cor cor) {
			super(tabuleiro);
			this.cor = cor;
		}

		public Cor getCor() {
			return cor;
		}
		
		public int getContadorMovimento() {
			return Contadormovimento;
		}
		
		public void incrementarContadorMovimento() {;
			Contadormovimento++;
		}
		
		public void decrementarContadorMovimento() {
			Contadormovimento--;
		}
		
		public PosicaoXadrez getPosicaoXadrez() {
			return PosicaoXadrez.dePosicao(posicao);
		}

		protected boolean temUmaPe�aOponente(Posicao posicao) {
			
			Pe�aXadrez p = (Pe�aXadrez)getTabuleiro().pe�a(posicao);
			return p!= null && p.getCor() != cor;
					
			}
}
