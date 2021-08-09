package me.senseiju.sentils.service

abstract class Service {
    open fun onDisable() {}
    open fun onReload() {}
}