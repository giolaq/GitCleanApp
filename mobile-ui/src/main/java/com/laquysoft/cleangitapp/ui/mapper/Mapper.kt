package com.laquysoft.cleangitapp.ui.mapper

/**
 * Interface for model mappers. It provides helper methods that facilitate
 * retrieving of models from outer layers
 *
 * @param <V> the view input type
 * @param <D> the view model output type
 */
interface Mapper<V, D> {

    fun mapToViewModel(type: D): V

    fun mapToView(type: V): D

}
