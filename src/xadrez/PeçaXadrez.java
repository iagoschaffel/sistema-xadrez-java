package xadrez;

import jogotabuleiro.Peça;
import jogotabuleiro.Posicao;
import jogotabuleiro.Tabuleiro;

public abstract class PeçaXadrez extends Peça {

		private Cor cor;

		public PeçaXadrez(Tabuleiro tabuleiro, Cor cor) {
			super(tabuleiro);
			this.cor = cor;
		}

		public Cor getCor() {
			return cor;
		}

		protected boolean temUmaPeçaOponente(Posicao posicao) {
			
			PeçaXadrez p = (PeçaXadrez)getTabuleiro().peça(posicao);
			return p!= null && p.getCor() != cor;
					
			}
}
