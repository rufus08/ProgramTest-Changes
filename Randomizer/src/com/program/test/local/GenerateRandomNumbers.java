package com.program.test.local;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.program.test.remote.RmiInterface;

public class GenerateRandomNumbers extends UnicastRemoteObject implements RmiInterface {

	private static final long serialVersionUID = 1L;
	List<RandomNumCheck> randomNums;
	
	protected GenerateRandomNumbers(List<RandomNumCheck> randomNums) throws RemoteException {
		super();
		this.randomNums = randomNums;
	}

	@Override
	public List<RandomNumCheck> generateRandomNumbers() throws RemoteException {
		return randomNums;
	}
	
	public static List<RandomNumCheck> populateRandomNums() {
		List<RandomNumCheck> randomList = new ArrayList<>();
		Random num = new Random();
		for (int i=0;i<5;i++) {
			int randomVal = num.nextInt(Integer.SIZE - 1);
			randomList.add(new RandomNumCheck(randomVal));
		}
		return randomList;
	}
	
	public static void main(String[] args) {
        try {
            Naming.rebind("//localhost/RandomNumbers", new GenerateRandomNumbers(populateRandomNums()));
            System.err.println("Server ready");
            RmiInterface look_up = (RmiInterface) Naming.lookup("//localhost/PrimeNumbers");
            if (look_up != null) {
            	for(RandomNumCheck random : look_up.generateRandomNumbers()) {//ranlook_up.generateRandomNumbers()){
            		System.out.println("The RANDOM number "+random.getRandomNum()+" is it prime number? "+random.getPrimeChk() );
            	}
            }
        } catch (Exception e) {
            System.err.println("Server exception: " + e.getMessage());
        }
	}
}