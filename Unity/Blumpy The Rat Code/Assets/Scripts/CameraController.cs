using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class CameraController : MonoBehaviour {

    public GameObject followTarget;
    private Vector3 targetposition;
    public float speed;

	
	void Update () {

        //Makes the camera follow the player and inputted speed

        targetposition = new Vector3(followTarget.transform.position.x, followTarget.transform.position.y, transform.position.z);
        transform.position = Vector3.Lerp(transform.position, targetposition, speed * Time.deltaTime);

    }
}
