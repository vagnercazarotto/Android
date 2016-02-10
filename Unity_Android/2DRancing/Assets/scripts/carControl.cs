using UnityEngine;
using System.Collections;

public class carControl : MonoBehaviour {

	public float carSpeed;
	Vector2 position;
	float maxPosition = 2.2f;


	// Use this for initialization
	void Start () {
		// current position of the car
		position = transform.position;

	}
	
	// Update is called once per frame
	void Update () {
		// increase the value of the position
		position.x += Input.GetAxis ("Horizontal") * carSpeed * Time.deltaTime;
		position.x = Mathf.Clamp (position.x, - maxPosition, maxPosition);
		// assing the position in the car
		transform.position = position;

		
	}

	void OnCollisionEnter2D(Collision2D col){
		if (col.gameObject.tag == "EnemyCar") {
			Destroy (gameObject);
				}
		}



}
