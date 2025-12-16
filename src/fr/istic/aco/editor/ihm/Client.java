package fr.istic.aco.editor.ihm;

import fr.istic.aco.editor.exceptions.EditorException;

public class Client {


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			IHM.myIHM();
		} catch (EditorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
