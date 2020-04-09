package xadrez;

import jogotabuleiro.Pe�a;
import jogotabuleiro.Tabuleiro;

public abstract class Pe�aXadrez extends Pe�a {

		private Cor cor;

		public Pe�aXadrez(Tabuleiro tabuleiro, Cor cor) {
			super(tabuleiro);
			this.cor = cor;
		}

		public Cor getCor() {
			return cor;
		}

				
}
