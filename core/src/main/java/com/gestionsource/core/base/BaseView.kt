package com.gestionsource.core.base

interface BaseView {
    fun showLoading()
    fun hideLoading()
    fun showError(message: String)
}