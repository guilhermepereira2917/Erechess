package enums;

public enum EstadosPartida {
    INICIANDO,
    EM_ANDAMENTO,
    EMPATADA,
    BRANCAS_VENCERAM,
    NEGRAS_VENCERAM;

	@Override
	public String toString() {
		switch (this) {
			case INICIANDO:
				return "Iniciando Partida";
			case EM_ANDAMENTO:
				return "Partida em Andamento";
			case EMPATADA:
				return "Partida Empatada";
			case BRANCAS_VENCERAM:
				return "Vitória das Brancas";
			case NEGRAS_VENCERAM:
				return "Vitória das Negras";
			default:
				return null;
		}
	}
}
