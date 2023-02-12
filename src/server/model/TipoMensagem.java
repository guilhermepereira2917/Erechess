package server.model;

public enum TipoMensagem {
    SAIR(0),
    JOGADA(1),
    MENSAGEM(2);

    public final byte valorByte;

    private TipoMensagem(int valorByte) {
        this.valorByte = (byte) valorByte;
    }

    public static TipoMensagem valorDaMensagem(int valorMensagem) {
        for (TipoMensagem mensagem : values()) {
            if (mensagem.valorByte == valorMensagem) {
                return mensagem;
            }
        }

        return null;
    }

}
