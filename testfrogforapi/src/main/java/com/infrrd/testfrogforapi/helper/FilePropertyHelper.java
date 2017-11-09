package com.infrrd.testfrogforapi.helper;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;


public class FilePropertyHelper
{

    // private constructor to avoid instantiation
    private FilePropertyHelper()
    {}

    private static final Map<String, Properties> PROPERTIES_MAP = new HashMap<>();


    /**
     * Gets the value of the key from the property file
     * @param propFile
     * @param key
     * @return
     */
    public static Optional<String> getProperty( String propFile, String key )
    {
        if ( !PROPERTIES_MAP.containsKey( propFile ) ) {
            synchronized ( PROPERTIES_MAP ) {
                if ( !PROPERTIES_MAP.containsKey( propFile ) ) {
                    try {
                        PROPERTIES_MAP.put( propFile, loadPropertyFile( propFile ) );
                    } catch ( IOException e ) {
                        return Optional.empty();
                    }
                }
            }
        }
        String value = PROPERTIES_MAP.get( propFile ).getProperty( key );
        if ( value != null && !value.isEmpty() ) {
            return Optional.of( value );
        } else {
            return Optional.empty();
        }
    }


    private static Properties loadPropertyFile( String propFile ) throws IOException
    {
        InputStream inputStream = FilePropertyHelper.class.getClassLoader().getResourceAsStream( propFile + ".properties" );
        Properties prop = new Properties();
        if ( inputStream != null ) {
            prop.load( inputStream );
        }
        return prop;
    }
}
