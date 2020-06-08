package ads.pipoca.model.dao;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import ads.pipoca.model.entity.Compra;

public class CompraDAO {
	public static final String SEP=";";
	
	public void gravarCompras(String path, ArrayList<Compra> compras) throws IOException {
		File fPath = new File(path);
		if(!fPath.exists()) {
			fPath.mkdir();
		}
		File outFile = new File(path,"compras.log");
		FileOutputStream outFileStream = new FileOutputStream(outFile);
		PrintWriter writer = new PrintWriter(outFileStream);
		String linha = "";
		System.out.println(outFile);
		for(Compra c:compras) {
			linha = c.getDataCompra().getTime()+SEP+c.getIdFilme()+SEP+c.getTituloFilme()+ SEP +c.getUsuario();
			System.out.println(linha);
			writer.println(linha);
		}
		writer.close();	
	}
}
