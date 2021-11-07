package br.edu.ifpb.gugawag.so.sockets;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Servidor2 {

    public static void main(String[] args) throws IOException {
    			
    	System.out.println("==========================");

    	Path dir = Paths.get("C:\\Users\\Micro\\Desktop\\GITHUB\\IFPB\\PDist\\files");
    	
    	DirectoryStream<Path> stream = Files.newDirectoryStream(dir, "*");

    	List<String> arqs = new ArrayList<>();
    	for (Path file : stream) {
    		arqs.add(file.toString());
 	    }
    	
        System.out.println("== Servidor ==");

        // Configurando o socket
        ServerSocket serverSocket = new ServerSocket(7001);
        Socket socket = serverSocket.accept();

        // pegando uma referência do canal de saída do socket. Ao escrever nesse canal, está se enviando dados para o
        // servidor
       
        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
        // pegando uma referência do canal de entrada do socket. Ao ler deste canal, está se recebendo os dados
        // enviados pelo servidor
        DataInputStream dis = new DataInputStream(socket.getInputStream());

        // laço infinito do servidor
        while (true) {
            System.out.println("Cliente: " + socket.getInetAddress());

            String mensagem = dis.readUTF();
            String[] msg = mensagem.split(" ");
            
            if (msg[0].equals("readdir")) {
            	dos.writeUTF(arqs.toString());
            }
            
            else if (msg[0].equals("rename")){
            
            	if (msg.length != 3) {
            		dos.writeUTF("Operação invalida");
            	}else {
            		
            		boolean alterado = false;
            		for (Path arq : stream) {
                		if (arq.toString().contains(msg[1])) {
                			File f = new File(msg[2]);
                			FileWriter fw = new FileWriter(f);
                			fw.append("a");
                			File fs = arq.toFile();
                			FileReader ftexto = new FileReader(arq.toFile());
                			fs.renameTo(f);
                			FileWriter fw2 = new FileWriter(fs);
                			fw2.append(ftexto.toString());
                			alterado = true;
                			
                		}
                	}
            		if(alterado) {
            			dos.writeUTF("Nome alterado com sucesso.");
            		}else {
            			dos.writeUTF("Arquivo não encontrado.");
            		}
            	}
            	
            }
            else{
            	dos.writeUTF("Opção invalida");
            }
            System.out.println(mensagem);

        }
        /*
         * Observe o while acima. Perceba que primeiro se lê a mensagem vinda do cliente (linha 29, depois se escreve
         * (linha 32) no canal de saída do socket. Isso ocorre da forma inversa do que ocorre no while do Cliente2,
         * pois, de outra forma, daria deadlock (se ambos quiserem ler da entrada ao mesmo tempo, por exemplo,
         * ninguém evoluiria, já que todos estariam aguardando.
         */

    }
}
