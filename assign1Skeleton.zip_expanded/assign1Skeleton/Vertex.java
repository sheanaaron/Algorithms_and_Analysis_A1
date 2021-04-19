	public class Vertex {
		private String vertLabel;
		private SIRState sirState;

		public Vertex(String vertLabel) {
			this.vertLabel = vertLabel;
			this.sirState = SIRState.S;
		}

		public String getVertLabel() {
			return vertLabel;
		}

		public SIRState getState() {
			return sirState;
		}

		public void setState() {
			if (this.sirState == SIRState.S) {
				this.sirState = SIRState.I;
			} else if (this.sirState == SIRState.I) {
				this.sirState = SIRState.R;
			}
		}
	}