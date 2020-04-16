package xadrez;

import jogotabuleiro.Peça;
import jogotabuleiro.Posicao;
import jogotabuleiro.Tabuleiro;

public abstract class PeçaXadrez extends Peça {

		private Cor cor;
		private int Contadormovimento;

		public PeçaXadrez(Tabuleiro tabuleiro, Cor cor) {
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

		protected boolean temUmaPeçaOponente(Posicao posicao) {
			
			PeçaXadrez p = (PeçaXadrez)getTabuleiro().peça(posicao);
			return p!= null && p.getCor() != cor;
					
			}
}
