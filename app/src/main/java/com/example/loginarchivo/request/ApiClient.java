package com.example.loginarchivo.request;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.loginarchivo.model.Usuario;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ApiClient {
    private  static SharedPreferences sp;

    private  static SharedPreferences conectar(Context context){
        if(sp==null){
            sp=context.getSharedPreferences("datos",0);
        }
        return sp;
    }

    public  static  void guardar(Context context, Usuario usuario){
        File carpeta=context.getFilesDir();
        File archivo=new  File (carpeta, "archivoUusarios.dat");
        //CREACION NODO CONEXION
        try{
            FileOutputStream fo=new FileOutputStream(archivo);
            BufferedOutputStream bo=new BufferedOutputStream(fo);
            DataOutputStream dos=new DataOutputStream(bo);

            dos.writeUTF(usuario.getDni());
            dos.writeUTF(usuario.getApellido());
            dos.writeUTF(usuario.getMail());
            dos.writeUTF(usuario.getNombre());
            dos.writeUTF(usuario.getPassword());

            bo.flush();
            fo.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public  static  Usuario leer(Context context, String correoBuscado){
        File carpeta=context.getFilesDir();
        File archivo=new  File (carpeta, "archivoUusarios.dat");
        Usuario miUsuario=new Usuario();
        //CREACION NODO CONEXION
        try{

            FileInputStream fi = new FileInputStream(archivo);
            BufferedInputStream bi=new BufferedInputStream(fi);
            DataInputStream dis=new DataInputStream(bi);
            String dni=null;
            String apellido=null;
            String nombre=null;
            String mail=null;
            String password=null;

            miUsuario=new Usuario();
            while ((mail=dis.readUTF())!=null){
                if(mail.equals(correoBuscado)) {
                    apellido = dis.readUTF();
                    nombre = dis.readUTF();
                    dni = dis.readUTF();
                    password = dis.readUTF();
                    miUsuario = new Usuario(dni, apellido, nombre, mail, password);
                }
            }
            fi.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  miUsuario;
    }
    public  static  boolean login(Context context,String correoBuscado,String passBuscado){

        File carpeta=context.getFilesDir();
        File archivo=new  File (carpeta, "archivoUusarios.dat");
        Usuario miUsuario=new Usuario();
        boolean rta=false;
        //CREACION NODO CONEXION
        try{
            FileInputStream fi = new FileInputStream(archivo);
            BufferedInputStream bi=new BufferedInputStream(fi);
            DataInputStream dis=new DataInputStream(bi);
            String mail=null;
            String password=null;

            miUsuario=new Usuario();
            while ((mail=dis.readUTF())!=null){
                password = dis.readUTF();
                if(mail.equals(correoBuscado) && password.equals(passBuscado)) rta=true;
            }
            fi.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  rta;
    }
}
