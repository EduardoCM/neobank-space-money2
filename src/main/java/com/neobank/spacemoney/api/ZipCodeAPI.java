package com.neobank.spacemoney.api;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.jboss.logging.Logger;

import com.neobank.spacemoney.model.CodigoPostal;

//// https://www.correosdemexico.gob.mx/SSLServicios/ConsultaCP/CodigoPostal_Exportar.aspx
@Path("/api/zipcode")
public class ZipCodeAPI {

	@Inject
	private Logger log;

	@GET
	@Path("/{zipcode}")
	public Response getZipCode(@PathParam("zipcode") String zipCode) {
		log.info("Codigo Postal: " + zipCode);
		List<CodigoPostal> codeInfoList = new ArrayList<CodigoPostal>();
		String file = "C:\\test\\CPdescarga.txt";
		String lineCode;

		try {
			BufferedReader buffer = new BufferedReader(
					new InputStreamReader(new FileInputStream(file), StandardCharsets.ISO_8859_1));

			while ((lineCode = buffer.readLine()) != null) {
				CodigoPostal codeInfo = new CodigoPostal();

				String separator = Pattern.quote("|");

				String[] codeDetail = lineCode.split(separator);

				if (codeDetail[0].equals(zipCode)) {

					codeInfo.codigo = codeDetail[0];
					codeInfo.asenta = codeDetail[1];
					codeInfo.tipoAsenta = codeDetail[2];
					codeInfo.municipio = codeDetail[3];
					codeInfo.estado = codeDetail[4];
					codeInfo.ciudad = codeDetail[5];

					codeInfoList.add(codeInfo);

				}

			}

			buffer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return Response.status(Status.ACCEPTED).entity(codeInfoList).build();

	}

}
