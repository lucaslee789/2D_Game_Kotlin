package com.example.user52.pokeshooting

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.example.user52.pokeshooting.Auth.sign_in
import com.example.user52.pokeshooting.Auth.sign_in_firebase
import com.example.user52.pokeshooting.Auth.sign_in_google
import com.example.user52.pokeshooting.Auth.sign_up
import com.example.user52.pokeshooting.Game.GameMenu
import com.example.user52.pokeshooting.Navi.SeekbarVolume
import com.example.user52.pokeshooting.Navi.YoutubePlay
import com.example.user52.pokeshooting.Navi.about_me
import com.example.user52.pokeshooting.Pixabay.PixaActivity
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.fragment_sign_in_firebase.*
import kotlinx.android.synthetic.main.fragment_sign_up.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, sign_in.OnFragmentInteractionListener, sign_in_firebase.OnFragmentInteractionListener, sign_in_google.OnFragmentInteractionListener, sign_up.OnFragmentInteractionListener, GoogleApiClient.OnConnectionFailedListener, about_me.OnFragmentInteractionListener{

    override fun onConnectionFailed(p0: ConnectionResult) {
        Log.e(TAG, "onConnectionFailed():" + p0);
        Toast.makeText(applicationContext, "Google Play Services error.", Toast.LENGTH_SHORT).show();
    }

    private val TAG = "FirebaseEmailPassword"

    private val REQUEST_CODE_SIGN_IN = 1234

    private lateinit var mAuth: FirebaseAuth

    private var mGoogleApiClient: GoogleApiClient? = null

    override fun onGSignInClicked(uri: View) {
        googlesignIn()
    }

    override fun onSignInClicked(uri: View) {
        signIn(fire_email.text.toString(), fire_password.text.toString())
    }

    override fun onRegisterClicked(uri: View){
        createAccount(et_email.text.toString(), et_password.text.toString())
    }

    /*
    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = mAuth.currentUser
        updateUI(currentUser)
    }
    */



    override fun SingUpPressed(uri: View) {
        System.out.println("Currently signed in !!, UID: " + mAuth.uid)
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.framelayout, sign_up())
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun SigninFirebase(uri: View) {
        System.out.println("Currently signed in !!, UID: " + mAuth.uid)
        val transaction2 = supportFragmentManager.beginTransaction()
        transaction2.replace(R.id.framelayout, sign_in_firebase())
        transaction2.addToBackStack(null)
        transaction2.commit()
    }

    override fun SigninGoogle(uri: View) {
        System.out.println("Currently signed in !!, UID: " + mAuth.uid)
        val transaction3 = supportFragmentManager.beginTransaction()
        transaction3.replace(R.id.framelayout, sign_in_google())
        transaction3.addToBackStack(null)
        transaction3.commit()
    }

    override fun onFragmentInteraction(uri: View) {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        //Action Bar
        val actionBar = supportActionBar

        actionBar!!.title = "Home Page"
        actionBar.subtitle = "Select the option"
        actionBar.elevation = 4.0F

        actionBar.setDisplayShowHomeEnabled(true)
        actionBar.setLogo(R.drawable.pokeball)
        actionBar.setDisplayUseLogoEnabled(true)


        mAuth = FirebaseAuth.getInstance()

        // Configure Google Sign In
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()

        mGoogleApiClient = GoogleApiClient.Builder(this)
                .enableAutoManage(this, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build()

        loadFront(sign_in())

        /*
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
        */

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
    }

    private fun loadFront(frag1: sign_in) {

        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.framelayout, frag1)
        ft.commit()
    }

    private fun createAccount(email: String, password: String) {
        Log.e(TAG, "createAccount:" + email)
        if (!validateForm()) {
            return
        }

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Log.e(TAG, "createAccount: Success!")

                        // update UI with the signed-in user's information
                        val user = mAuth.currentUser
                        updateUI(user)
                    } else {
                        Log.e(TAG, "createAccount: Fail!", task.exception)
                        Toast.makeText(applicationContext, "Authentication failed!", Toast.LENGTH_SHORT).show()
                        updateUI(null)
                    }
                }
    }

    private fun signIn(email: String, password: String) {

        if (!validateForm2()) {
            return
        }

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Log.e(TAG, "signIn: Success!")
                        System.out.println("Log in Successed")

                        // update UI with the signed-in user's information
                        val user = mAuth.getCurrentUser()
                        updateUI2(user)
                    } else {
                        Log.e(TAG, "signIn: Fail!", task.exception)
                        Toast.makeText(applicationContext, "Authentication failed!", Toast.LENGTH_SHORT).show()
                        updateUI2(null)
                    }
                }
    }

    //-------------------Fire base sign in $ sign up

    private fun googlesignIn() {
        val intent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient)
        startActivityForResult(intent, REQUEST_CODE_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent();
        if (requestCode == REQUEST_CODE_SIGN_IN) {
            val result = Auth.GoogleSignInApi.getSignInResultFromIntent(data)
            if (result.isSuccess) {
                // successful -> authenticate with Firebase
                val account = result.signInAccount
                firebaseAuthWithGoogle(account!!)
            } else {
                // failed -> update UI
                updateUI(null)
                Toast.makeText(applicationContext, "SignIn: failed!",
                        Toast.LENGTH_SHORT).show()
            }
        }
    }



    private fun firebaseAuthWithGoogle(acct: GoogleSignInAccount) {
        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.id!!)

        val credential = GoogleAuthProvider.getCredential(acct.idToken, null)
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "signInWithCredential:success")
                        val user = mAuth.currentUser
                        updateUI2(user)
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "signInWithCredential:failure", task.exception)
                        updateUI2(null)
                    }
                }
    }

    //________google sign in and sign up

    private fun validateForm(): Boolean {

        var valid = true

        val email = et_email.text.toString()
        if (TextUtils.isEmpty(email)) {
            et_email.error = "Required."
            valid = false
        } else {
            et_email.error = null
        }

        val password = et_password.text.toString()
        if (TextUtils.isEmpty(password)) {
            et_password.error = "Required."
            valid = false
        } else {
            et_password.error = null
        }

        return valid
    }


    private fun validateForm2(): Boolean {

        var valid = true

        val email = fire_email.text.toString()
        if (TextUtils.isEmpty(email)) {
            fire_email.error = "Required."
            valid = false
        } else {
            fire_email.error = null
        }

        val password = fire_password.text.toString()
        if (TextUtils.isEmpty(password)) {
            fire_password.error = "Required."
            valid = false
        } else {
            fire_password.error = null
        }

        return valid
    }

    private fun updateUI(user: FirebaseUser?) {

        if (user != null) {
            System.out.println("**********Currently signed in !!, UID: " + mAuth.uid)
            loadFront(sign_in())
            //Toast.makeText(applicationContext, "Successfully Created Please Log in", Toast.LENGTH_SHORT).show()
        }
        else {
            Toast.makeText(applicationContext, "Already existing ID", Toast.LENGTH_SHORT).show()
        }
    }

    private fun updateUI2(user: FirebaseUser?) {

        if (user != null) {
            System.out.println("******************************Currently signed in !!, UID: " + mAuth.uid)
            val intent = Intent(this, GameMenu::class.java)
            startActivity(intent)
        }
        else {
            Toast.makeText(applicationContext, "Login Failed", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> {
                val intent = Intent(this, SeekbarVolume::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_camera -> {
                val intent = Intent(this, PixaActivity::class.java)
                startActivity(intent)
            }
            R.id.nav_gallery -> {
                val intent = Intent(this, YoutubePlay::class.java)
                startActivity(intent)
            }
            R.id.nav_about_dev -> {
                val manager = supportFragmentManager
                val transaction = manager.beginTransaction()
                transaction.replace(R.id.framelayout, about_me())
                transaction.addToBackStack(null)
                transaction.commit()
            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
