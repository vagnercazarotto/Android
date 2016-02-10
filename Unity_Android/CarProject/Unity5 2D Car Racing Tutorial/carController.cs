using UnityEngine;
using System.Collections;



public class carController : MonoBehaviour {

	public float carSpeed;
	public float maxPos = 2.2f;

	Vector3 position;
	public uiManager ui;

	void Awake(){

	}
	
	void Start () {
		//ui = GetComponent<uiManager> ();
		position = transform.position;

	}
	

	void Update () {


		position.x += Input.GetAxis ("Horizontal") * carSpeed * Time.deltaTime;
		position.x = Mathf.Clamp (position.x, -2.2f, 2.2f);

		transform.position = position;

	}

	void OnCollisionEnter2D(Collision2D col){
		if (col.gameObject.tag == "Enemy Car") {
			Destroy (gameObject);
			ui.gameOverActivated();




		}
	}


}
