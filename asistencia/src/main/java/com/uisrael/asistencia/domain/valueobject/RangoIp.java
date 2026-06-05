package com.uisrael.asistencia.domain.valueobject;

public record RangoIp(String cidr) {

    public RangoIp {
        if (cidr == null || cidr.isBlank()) {
            throw new IllegalArgumentException("El rango IP no puede estar vacío");
        }
        if (!cidr.matches("^(\\d{1,3}\\.){3}\\d{1,3}/\\d{1,2}$")) {
            throw new IllegalArgumentException("Formato CIDR inválido: " + cidr);
        }
    }
    public boolean contiene(String ip) {
        try {
            String[] partes = cidr.split("/");
            String[] redBytes = partes[0].split("\\.");
            int prefijo = Integer.parseInt(partes[1]);

            int red = ipAEntero(redBytes);
            int mascara = prefijo == 0 ? 0 : (0xFFFFFFFF << (32 - prefijo));
            int ipEntero = ipAEntero(ip.split("\\."));

            return (red & mascara) == (ipEntero & mascara);
        } catch (Exception e) {
            return false;
        }
    }
    private int ipAEntero(String[] partes) {
        int resultado = 0;
        for (String parte : partes) {
            resultado = (resultado << 8) | Integer.parseInt(parte);
        }
        return resultado;
    }
    
    @Override
    public String toString() {
        return cidr;
    }
}