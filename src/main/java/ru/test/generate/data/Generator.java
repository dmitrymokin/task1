package ru.test.generate.data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Random;
public class Generator {

    Random random;

    public Generator() {
         random = new Random();
    }

    public LocalDate getDate() {
        final int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        int minDay = (int) LocalDate.of(currentYear + Variable.MIN_YEAR_OF_CURRENT, Variable.MIN_MONTH, Variable.MIN_DAY).toEpochDay();
        int maxDay = (int) LocalDate.of(currentYear+Variable.MAX_YEAR_OF_CURRENT, Variable.MAX_MONTH, Variable.MAX_DAY).toEpochDay();
        long randomDay = minDay + random.nextInt(maxDay - minDay);
        LocalDate randomBirthDate = LocalDate.ofEpochDay(randomDay);
        return randomBirthDate;
    }

    public LocalTime getTime() {
        LocalTime time = LocalTime.MIN.minusHours(random.nextLong());
        return time;
    }

    public BigDecimal getSum() {
        Integer i = random.nextInt(( Variable.MAX_SUM1 - Variable.MIN_SUM1) + 1) + Variable.MIN_SUM1;
        Double d = (random.nextInt((Variable.MAX_SUM2 -  Variable.MIN_SUM2) + 1) +  Variable.MIN_SUM2)/100.0;
        return BigDecimal.valueOf(i+d).setScale(Variable.SCALE,BigDecimal.ROUND_HALF_UP);
    }

    public Integer getInt(Integer min, Integer max) {
        return random.nextInt((max - min) + 1) + min;
    }

    public static void main(String[] args) {

        Integer countFiles = args.length-2;
        Integer count = Integer.valueOf(args[1]);
        Integer size1 = count/countFiles;
        Integer size2 = count - (size1 * countFiles);

        Generator generator = new Generator();

        for (int i=0; i<countFiles; i++) {
            FileWriter f = new FileWriter();
            f.write(args[0], args[i+2], generator, size1);
        }

        if (size2 !=0 ) {
            FileWriter f = new FileWriter();
            f.write(args[0], Variable.FILE_NAME_MOD, generator, size2);
        }
    }
}
