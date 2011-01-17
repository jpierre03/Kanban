package fr.prunetwork.network.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * @author Marc-Emmanuel Bellemare Jan. 2005
 * @author jpierre03
 */
public class MonClient{

	/**
	 *
	 */
	private Socket client = null;
	/**
	 *
	 */
	private BufferedReader entree;
	/**
	 *
	 */
	private PrintWriter sortie;

	/** Le constructeur qui indique sur quel serveur se connecter et quel port solliciter
	 * @param hostName
	 * @param port le numero de port
	 * @throws IOException si erreur de connexion
	 */
	public MonClient(String hostName, int port) throws IOException{
		try{
			// Convertir la chaine de caractères "hostName" en une adresse IP valide du serveur
			InetAddress adresseIP = InetAddress.getByName(hostName);            //récupère l'adresse IP à partir du nom de machine
			//creer le Socket vers le serveur
			client = new Socket(adresseIP, port);                               // création d'un socket avec la config en param
			//on fixe un timeOut
			client.setSoTimeout(1000);                                          // on définit une durée au dela de laquelle on abandonne
		} catch(UnknownHostException e){
			System.err.println("Je ne connais pas le serveur: " + hostName);
			throw e;
		} catch(SocketException e){
			System.err.println("erreur de connexion ou timeout");
			throw e;
		} catch(IOException e){
			System.err.println("Probleme de connexion sur:" + hostName);
			throw e;
		}
		System.out.println("Connexion OK sur " + hostName);

		try{
			entree = new BufferedReader(
					new InputStreamReader(client.getInputStream()));    //crée un flux à partir du socket client
			sortie = new PrintWriter(client.getOutputStream());                 //crée un flux à partir du socket client
		} catch(IOException e){
			System.err.println("PB creation des streams");
			System.exit(1);
		}

	}

	/** lit les caracteres envoyés par le serveur.
	 * @return un objet String qui contient l'ensemble des caractères lus
	 */
	public String lireServeur(){
		String ligne = null;
		try{
			ligne = entree.readLine();
		} catch(IOException e){
			//System.err.println("rien a lire");
		}
		return ligne;
	}

	/**
	 * Renvoie un identifiant de Socket
	 * @return un identifiant de Socket
	 */
	Socket getClient(){
		return client;
	}

	/**
	 * Envoie des données au serveur.
	 * @param ligne les caractères à envoyer
	 */
	public void ecrireServeur(String ligne){
		sortie.println(ligne);
		sortie.flush();
	}

	/**
	 * Teste la connexion.
	 *@return un booleen notifiant l'état de la connexion
	 */
	public boolean estConnect(){
		return client.isConnected();
	}

	/**
	 * Fermeture du socket & du lclient en général.
	 */
	public void fermer(){
		try{
			entree.close();
			sortie.close();
			if(client != null){
				client.close();
			}
			System.out.println("Fermeture ok");
		} catch(IOException e){
			System.err.println("Erreur à la fermeture des flux!");
		}
	}

	/**
	 * Méthode invoquée lors du passage du ramasse miette
	 */
	@Override
	protected void finalize(){
		fermer();
	}
}
