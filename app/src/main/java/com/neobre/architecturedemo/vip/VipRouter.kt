package com.neobre.architecturedemo.vip

import androidx.navigation.NavController

class VipRouter(private val navController: NavController) : VipContract.Router {
    override fun back() {
        navController.popBackStack()
    }
}
