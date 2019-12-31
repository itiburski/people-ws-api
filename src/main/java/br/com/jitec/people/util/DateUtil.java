package br.com.jitec.people.util;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

public class DateUtil {

	public static Integer calcularIdade(Date dataNascimento) {
		if (dataNascimento == null) {
			return null;
		}
		LocalDate dn = dataNascimento.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		Period periodo = Period.between(dn, LocalDate.now());

		return periodo.getYears();
	}

}
