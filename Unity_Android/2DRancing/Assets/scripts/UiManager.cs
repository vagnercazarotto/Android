﻿using UnityEngine;
using System.Collections;

public class UiManager : MonoBehaviour {

	// Use this for initialization
	void Start () {
	
	}
	
	// Update is called once per frame
	void Update () {
	
	}

	public void Pause(){
		if (Time.timeScale == 1) {
			Time.timeScale =0;
		}else if(Time.timeScale == 0){
			Time.timeScale = 1;
		}
	}

	public void Play(){
		Application.LoadLevel ("level1");
		}

}
