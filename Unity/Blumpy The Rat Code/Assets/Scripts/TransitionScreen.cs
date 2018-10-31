using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;

public class TransitionScreen : MonoBehaviour {

    
    public float timer = 5;     //float that assign value to timer

	void Start () {

       
	}
	
	// Update is called once per frame
	void Update () {
        timer -= Time.deltaTime;  //Start dedduction Time.deltaTime from the timer (countdown)
        
        if (timer <= 0) //Loads next scene when timer reaches 0
        {
            SceneManager.LoadScene(SceneManager.GetActiveScene().buildIndex + 1);
        }
	}
}
