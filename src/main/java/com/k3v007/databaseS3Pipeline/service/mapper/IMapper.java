package com.k3v007.databaseS3Pipeline.service.mapper;

/**
 * The interface Mapper.
 *
 * @param <T> the type parameter
 * @param <U> the type parameter
 * @author Vivek
 */
public interface IMapper<T, U> {

    /**
     * Map u.
     *
     * @param data the data
     * @return the u
     */
    U map(T data);
}
