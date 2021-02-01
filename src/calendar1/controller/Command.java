package calendar1.controller;

public enum Command {

    NEWEVENT("El evento ha sido creado"),
    SHOWEVENT("Los eventos han sido mostrados"),
    REMOVEEVENT("El evento ha sido borrado"),
    HELP("End of Help"),
    SHOWTODAYEVENTS("Estos son todos los eventos de hoy"),
    SHOWTHISDAYEVENTS("Estos son todos los eventos del día seleccionado");

    private final String message;

    Command(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }


}
