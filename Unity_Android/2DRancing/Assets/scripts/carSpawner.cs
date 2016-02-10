using UnityEngine;
using System.Collections;

public class carSpawner : MonoBehaviour {
		
	public GameObject[] cars;
	public float maxPos = 2f;
	public float delayTimer = 0.5f;
	float timer;
	int carNumber;

	// Use this for initialization
	void Start () {
		timer = delayTimer;
	}


	
	// Update is called once per frame
	void Update () {
		timer -= Time.deltaTime;
		if(timer <= 0){

			Vector3 carPos = new Vector3 (Random.Range (-1.8f, 1.8f), transform.position.y, transform.position.z);
			carNumber = Random.Range (0,9);
			Instantiate (cars[carNumber], carPos , transform.rotation);
			timer = delayTimer;
		}
	}
}
