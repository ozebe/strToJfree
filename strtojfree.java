package main;

import java.util.ArrayList;
import java.util.List;
import org.jfree.data.time.Day;
import org.jfree.data.time.Hour;
import org.jfree.data.time.Minute;

/**
 * Classe para realizar a conversão das Strings fornecidas para os tipos
 * necessários, atualmente é recomendada para analise de dados de temperatura ou
 * pressão.
 *
 * @author Wesley Ozebe
 * @version 2.0
 * @since Release 02 da aplicação
 */
public class Converter2 {

    private List l = new ArrayList();
    private List<Day> datas = new ArrayList<Day>();
    private List<Hour> horas = new ArrayList<Hour>();
    private List<Minute> minutos = new ArrayList<Minute>();
    private String regex;
    private String dado;

    public static class Builder {

        // requerido
        private List l = new ArrayList();
        private List<Day> datas = new ArrayList<Day>();
        private List<Hour> horas = new ArrayList<Hour>();
        private List<Minute> minutos = new ArrayList<Minute>();
        private String regex;
        private String dado;

        /**
         * @author Wesley Ozebe Construtor
         * @param regex String - A ser pesquisada
         * @param dado String - Sem modificações onde será realizada a pesquisa.
         */
        public Builder(String dado, String regex) {
            this.dado = dado;
            this.regex = regex;
        }

        //retorna uma lista com o padrao(regex) passado no Builder
        /**
         * Conversão de dados para list
         *
         * @author Wesley Ozebe
         * @return List - para acessar seus valores utilize o getL()
         */
        public Builder converter() {
            c();
            return this;
        }

        /**
         * Conversão de dados para list de org.jfree.data.time.Day
         *
         * @author Wesley Ozebe
         * @return List - para acessar seus valores utilize o getDatas(), ex:
         * 28-maio-2019
         */
        public Builder converterData() {
            cDate(c());
            return this;
        }

        /**
         * Conversão de dados para list de org.jfree.data.time.Hour
         *
         * @author Wesley Ozebe
         * @return List - para acessar seus valores utilize o getHoras(), ex de
         * retorno: [17,28/5/2019]
         */
        public Builder converterHora() {
            List hh = new ArrayList();
            List dd = new ArrayList();
            Converter2 data2 = new Converter2.Builder(dado, "data").converter().build();
            Converter2 hora2 = new Converter2.Builder(dado, "hora").converter().build();
            for (int i = 0; i < data2.getL().size(); i++) {
                dd.add(data2.getL().get(i));
            }
            for (int i = 0; i < hora2.getL().size(); i++) {
                hh.add(hora2.getL().get(i));
            }
            cHour(hh, dd);
            return this;
        }

        /**
         * Conversão de dados para list de org.jfree.data.time.Minute
         *
         * @author Wesley Ozebe
         * @return List - para acessar seus valores utilize o getMinutos(), ex
         * de retorno: Tue May 28 17:35:00 BRT 2019
         */
        public Builder converterMinutos() {
            List hh = new ArrayList();
            List dd = new ArrayList();
            Converter2 data2 = new Converter2.Builder(dado, "data").converter().build();
            Converter2 hora2 = new Converter2.Builder(dado, "hora").converter().build();

            for (int i = 0; i < data2.getL().size(); i++) {
                dd.add(data2.getL().get(i));
            }

            for (int i = 0; i < hora2.getL().size(); i++) {
                hh.add(hora2.getL().get(i));
            }
            cMinute(hh, dd);
            return this;
        }

        /**
         * Método para retorno de uma Lista utilizando-se o pattern passado
         *
         * @author Wesley Ozebe pesquisa.
         * @return List - Lista com os valores
         */
        private List c() {
            this.dado = this.dado.replaceAll("\r", "");
            this.dado = this.dado.replaceAll("\t", " ");
            this.dado = this.dado.replaceAll("\n", "");
            this.dado = this.dado.replaceAll(" ", "");
            this.dado = this.dado.replaceAll("/", ",");

            String array[];
            array = this.dado.split(";");
            array = this.dado.split("=");
            array = this.dado.split(";");
            for (int i = 0; i < array.length; i++) {
                String string = array[i];
                if (string.matches(".*(?i)" + regex + ".*")) {
                    String[] stringDividida = string.split("=");
                    l.add(stringDividida[1]);

                }

            }

            return l;
        }

        /**
         * Método para retorno de uma Lista de datas no modelo JFreechart
         * (org.jfree.data.time.Day)
         *
         * @author Wesley Ozebe
         * @param date List - Lista de datas em formato string.
         * @return List - Lista no formato JFreechart Day
         */
        private List cDate(List date) {
            List dias = new ArrayList();
            List meses = new ArrayList();
            List anos = new ArrayList();
            for (int i = 0; i < date.size(); i++) {
                String d = date.get(i).toString();
                d = d.replaceAll("\\s+", "");
                String array[];
                array = d.split(",");
                dias.add(array[0]);
                meses.add(array[1]);
                anos.add(array[2]);

                Day d01 = new Day(Integer.parseInt(dias.get(i).toString()), Integer.parseInt(meses.get(i).toString()), Integer.parseInt(anos.get(i).toString()));
                this.datas.add(d01);
            }
            return this.datas;

        }

        /**
         * Método para retorno de uma Lista de horas no modelo JFreechart
         * (org.jfree.data.time.Hour)
         *
         * @author Wesley Ozebe
         * @param date List - Lista de datas em formato string.
         * @param hour List - Lista de horas em formato string.
         * @return List - Lista no formato JFreechart Hour
         */
        private List cHour(List hour, List date) {
            List dias = new ArrayList();
            List meses = new ArrayList();
            List anos = new ArrayList();
            List horasList = new ArrayList();
            for (int i = 0; i < hour.size(); i++) {
                String d = date.get(i).toString();
                d = d.replaceAll("\\s+", "");
                String array[];
                array = d.split(",");
                dias.add(array[0]);
                meses.add(array[1]);
                anos.add(array[2]);

                Day d01 = new Day(Integer.parseInt(dias.get(i).toString()), Integer.parseInt(meses.get(i).toString()), Integer.parseInt(anos.get(i).toString()));
                String d2 = hour.get(i).toString();
                String array02[];
                array02 = d2.split(":");
                horasList.add(array02[0]);

                Hour h = new Hour(Integer.parseInt(horasList.get(i).toString()), d01);
                horas.add(h);

            }

            return horas;
        }

        /**
         * Método para retorno de uma Lista de minutos no modelo JFreechart
         * (org.jfree.data.time.Minute)
         *
         * @author Wesley Ozebe
         * @param date List - Lista de datas em formato string.
         * @param hour List - Lista de horas em formato string.
         * @return List - Lista no formato JFreechart Minute
         */
        private List cMinute(List hour, List date) {

            List dias = new ArrayList();
            List meses = new ArrayList();
            List anos = new ArrayList();
            List horasList = new ArrayList();
            List minutosList = new ArrayList();
            for (int i = 0; i < hour.size(); i++) {
                String d = date.get(i).toString();
                d = d.replaceAll("\\s+", "");
                String array[];
                array = d.split(",");
                dias.add(array[0]);
                meses.add(array[1]);
                anos.add(array[2]);

                Day d01 = new Day(Integer.parseInt(dias.get(i).toString()), Integer.parseInt(meses.get(i).toString()), Integer.parseInt(anos.get(i).toString()));
                String d2 = hour.get(i).toString();
                String array02[];
                array02 = d2.split(":");
                horasList.add(array02[0]);
                minutosList.add(array02[1]);

                Hour h = new Hour(Integer.parseInt(horasList.get(i).toString()), d01);
                Minute m = new Minute(Integer.parseInt(minutosList.get(i).toString()), h);
                minutos.add(m);

            }

            return minutos;
        }

        /**
         * *
         * @return Converter - o build constroi a classe com os parametos, utilize-o no final
         * exemplo:
         * Converter h = new Converter.Builder(dado, "hora").converterHora().build();
         * passados
         */
        public Converter2 build() {
            return new Converter2(this);
        }

    }
    
    private Converter2(Builder builder) {
        dado = builder.dado;
        regex = builder.regex;
        l = builder.l;
        datas = builder.datas;
        horas = builder.horas;
        minutos = builder.minutos;
    }

    /**
     * Método para acesso a Lista de datas criada utilizando-se o pattern
     * passado
     *
     * @author Wesley Ozebe
     * @return List - Retorna uma lista de datas org.jfree.data.time.Day no
     * seguinte padrao: DD-MM-AAAA, ex: 28-maio-2019
     */
    public List<Day> getDatas() {
        return datas;
    }

    /**
     * Método para acesso a Lista de horas criada utilizando-se o pattern
     * passado
     *
     * @author Wesley Ozebe
     * @return List - Retorna uma lista de horas org.jfree.data.time.Hour no
     * seguinte padrao: [HH,DD/MM/AAAA], ex: [17,28/5/2019]
     */
    public List<Hour> getHoras() {
        return horas;
    }

    /**
     * Método para acesso a Lista de minutos e respectivas horas e dias criada
     * utilizando-se o pattern passado
     *
     * @author Wesley Ozebe
     * @return List - Retorna uma lista de minutos org.jfree.data.time.Minute no
     * seguinte padrao: DIASEMANA MM DD HH:MM:SS TIMEZONE AAAA, ex: Tue May 28
     * 17:35:00 BRT 2019
     */
    public List<Minute> getMinutos() {
        return minutos;
    }

    /**
     * Método para acesso a Lista criada utilizando-se o pattern passado
     *
     * @author Wesley Ozebe
     * @return List - Retorna uma lista com os valores selecionados
     */
    public List getL() {
        return l;
    }

}
