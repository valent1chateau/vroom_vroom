/**
 * $Id$
 * 
 * Copyright (c) 2011-17 Stephane GALLAND <stephane.galland@utbm.fr>.
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 * This program is free software; you can redistribute it and/or modify
 */
package traffic_simulation.util;

import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import java.io.InputStream;
import java.net.URL;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.eclipse.xtext.xbase.lib.Inline;
import org.eclipse.xtext.xbase.lib.Pure;
import org.eclipse.xtext.xbase.lib.StringExtensions;

/**
 * This utility class provides to load resources according to
 * several heuristics:<ul>
 * <li>search the resource in class paths;</li>
 * <li>search the resource in ./resources subdirectory in class paths.</li>
 * </ul>
 * 
 * Copied from {@link https://github.com/gallandarakhneorg/afc/}.
 * 
 * @author St&eacute;phane GALLAND &lt;stephane.galland@utbm.fr&gt;
 * @version $Name$ $Revision$ $Date$
 */
@SarlSpecification("0.11")
@SarlElementType(10)
@SuppressWarnings("all")
public final class Resources {
  /**
   * Character used to separate paths on an resource name.
   */
  public static final String NAME_SEPARATOR = "/";
  
  /**
   * Prefix (or directory name) where resources may be located.
   */
  public static final String RESOURCE_PREFIX = "resources/";
  
  /**
   * Replies the URL of a resource.
   * <p>
   * You may use Unix-like syntax to write the resource path, ie.
   * you may use slashes to separate filenames.
   * <p>
   * The class loader replied by {@link ClassLoaderFinder} is used.
   * If it is <code>null</code>, the class loader of
   * the Resources class is used.
   * 
   * @param path is the absolute path of the resource.
   * @return the url of the resource or <code>null</code> if the resource was
   * not found in class paths.
   */
  @Pure
  public static URL getResource(final String path) {
    final ClassLoader cl = null;
    return Resources.getResource(cl, path);
  }
  
  /**
   * Replies the URL of a resource.
   * <p>
   * You may use Unix-like syntax to write the resource path, ie.
   * you may use slashes to separate filenames.
   * <p>
   * The name of <var>packagename</var> is translated into a resource
   * path (by replacing the dots by slashes) and the given path
   * is append to. For example, the two following codes are equivalent:<pre><code>
   * Resources.getResources(Package.getPackage("org.arakhne.afc"), "/a/b/c/d.png");
   * Resources.getResources("org/arakhne/afc/a/b/c/d.png");
   * </code></pre>
   * <p>
   * If the <var>classLoader</var> parameter is <code>null</code>,
   * the class loader replied by {@link ClassLoaderFinder} is used.
   * If this last is <code>null</code>, the class loader of
   * the Resources class is used.
   * 
   * @param classLoader is the research scope. If <code>null</code>,
   * the class loader replied by {@link ClassLoaderFinder} is used.
   * @param packagename is the package in which the resource should be located.
   * @param path is the relative path of the resource in the package.
   * @return the url of the resource or <code>null</code> if the resource was
   * not found in class paths.
   * @since 6.2
   */
  @Pure
  public static URL getResource(final ClassLoader classLoader, final Package packagename, final String path) {
    if (((packagename == null) || (path == null))) {
      return null;
    }
    StringBuilder b = new StringBuilder();
    b.append(
      packagename.getName().replaceAll(
        Pattern.quote("."), 
        Matcher.quoteReplacement(Resources.NAME_SEPARATOR)));
    boolean _startsWith = path.startsWith(Resources.NAME_SEPARATOR);
    if ((!_startsWith)) {
      b.append(Resources.NAME_SEPARATOR);
    }
    b.append(path);
    return Resources.getResource(packagename.getClass().getClassLoader(), b.toString());
  }
  
  /**
   * Replies the URL of a resource.
   * <p>
   * You may use Unix-like syntax to write the resource path, ie.
   * you may use slashes to separate filenames.
   * <p>
   * The name of <var>classname</var> is translated into a resource
   * path (by remove the name of the class and replacing the dots by slashes) and the given path
   * is append to. For example, the two following codes are equivalent:<pre><code>
   * Resources.getResources(Resources.class, "/a/b/c/d.png");
   * Resources.getResources("org/arakhne/vmutil/a/b/c/d.png");
   * </code></pre>
   * <p>
   * The class loader of the given class is used. If it is <code>null</code>,
   * the class loader replied by {@link ClassLoaderFinder} is used.
   * If it is also <code>null</code>, the class loader of this Resources
   * class is used.
   * 
   * @param classname is located in the package in which the resource should be also located.
   * @param path is the absolute path of the resource.
   * @return the url of the resource or <code>null</code> if the resource was
   * not found in class paths.
   */
  @Pure
  public static URL getResource(final Class<?> classname, final String path) {
    if ((classname == null)) {
      return null;
    }
    URL u = Resources.getResource(classname.getClassLoader(), classname.getPackage(), path);
    if ((u == null)) {
      u = Resources.getResource(classname.getClassLoader(), path);
    }
    return u;
  }
  
  /**
   * Replies the URL of a resource.
   * <p>
   * You may use Unix-like syntax to write the resource path, ie.
   * you may use slashes to separate filenames.
   * <p>
   * If the <var>classLoader</var> parameter is <code>null</code>,
   * the class loader replied by {@link ClassLoaderFinder} is used.
   * If this last is <code>null</code>, the class loader of
   * the Resources class is used.
   * 
   * @param classLoader is the research scope. If <code>null</code>,
   * the class loader replied by {@link ClassLoaderFinder} is used.
   * @param path is the absolute path of the resource.
   * @return the url of the resource or <code>null</code> if the resource was
   * not found in class paths.
   */
  @Pure
  public static URL getResource(final ClassLoader classLoader, final String path) {
    if ((path == null)) {
      return null;
    }
    String resourcePath = path;
    boolean _startsWith = path.startsWith("/");
    if (_startsWith) {
      resourcePath = path.substring(1);
    }
    ClassLoader _xifexpression = null;
    if ((classLoader == null)) {
      _xifexpression = Resources.class.getClassLoader();
    } else {
      _xifexpression = classLoader;
    }
    ClassLoader loader = _xifexpression;
    class $AssertEvaluator$ {
      final boolean $$result;
      $AssertEvaluator$(final ClassLoader loader) {
        this.$$result = (loader != null);
      }
    }
    assert new $AssertEvaluator$(loader).$$result;
    URL url = loader.getResource(resourcePath);
    if ((url == null)) {
      url = loader.getResource((Resources.RESOURCE_PREFIX + resourcePath));
    }
    return url;
  }
  
  /**
   * Replies the input stream of a resource.
   * <p>
   * You may use Unix-like syntax to write the resource path, ie.
   * you may use slashes to separate filenames, and may not start the
   * path with a slash.
   * <p>
   * The class loader replied by {@link ClassLoaderFinder} is used.
   * If it is <code>null</code>, the class loader of
   * the Resources class is used.
   * 
   * @param path is the absolute path of the resource.
   * @return the url of the resource or <code>null</code> if the resource was
   * not found in class paths.
   */
  @Pure
  public static InputStream getResourceAsStream(final String path) {
    final ClassLoader cl = null;
    return Resources.getResourceAsStream(cl, path);
  }
  
  /**
   * Replies the input stream of a resource.
   * <p>
   * You may use Unix-like syntax to write the resource path, ie.
   * you may use slashes to separate filenames.
   * <p>
   * The name of <var>packagename</var> is translated into a resource
   * path (by replacing the dots by slashes) and the given path
   * is append to. For example, the two following codes are equivalent:<pre><code>
   * Resources.getResources(Package.getPackage("org.arakhne.afc"), "/a/b/c/d.png");
   * Resources.getResources("org/arakhne/afc/a/b/c/d.png");
   * </code></pre>
   * <p>
   * If the <var>classLoader</var> parameter is <code>null</code>,
   * the class loader replied by {@link ClassLoaderFinder} is used.
   * If this last is <code>null</code>, the class loader of
   * the Resources class is used.
   * 
   * @param classLoader is the research scope. If <code>null</code>,
   * the class loader replied by {@link ClassLoaderFinder} is used.
   * @param packagename is the package in which the resource should be located.
   * @param path is the relative path of the resource in the package.
   * @return the url of the resource or <code>null</code> if the resource was
   * not found in class paths.
   * @since 6.2
   */
  @Pure
  public static InputStream getResourceAsStream(final ClassLoader classLoader, final Package packagename, final String path) {
    if (((packagename == null) || (path == null))) {
      return null;
    }
    StringBuilder b = new StringBuilder();
    b.append(
      packagename.getName().replaceAll(
        Pattern.quote("."), 
        Matcher.quoteReplacement(Resources.NAME_SEPARATOR)));
    boolean _startsWith = path.startsWith(Resources.NAME_SEPARATOR);
    if ((!_startsWith)) {
      b.append(Resources.NAME_SEPARATOR);
    }
    b.append(path);
    return Resources.getResourceAsStream(classLoader, b.toString());
  }
  
  /**
   * Replies the input stream of a resource.
   * <p>
   * You may use Unix-like syntax to write the resource path, ie.
   * you may use slashes to separate filenames.
   * <p>
   * The name of <var>classname</var> is translated into a resource
   * path (by remove the name of the class and replacing the dots by slashes) and the given path
   * is append to. For example, the two following codes are equivalent:<pre><code>
   * Resources.getResources(Resources.class, "/a/b/c/d.png");
   * Resources.getResources("org/arakhne/vmutil/a/b/c/d.png");
   * </code></pre>
   * <p>
   * The class loader of the given class is used. If it is <code>null</code>,
   * the class loader replied by {@link ClassLoaderFinder} is used.
   * If it is also <code>null</code>, the class loader of this Resources
   * class is used.
   * 
   * @param classname is located in the package in which the resource should be also located.
   * @param path is the absolute path of the resource.
   * @return the url of the resource or <code>null</code> if the resource was
   * not found in class paths.
   */
  @Pure
  public static InputStream getResourceAsStream(final Class<?> classname, final String path) {
    if ((classname == null)) {
      return null;
    }
    InputStream is = Resources.getResourceAsStream(classname.getClassLoader(), classname.getPackage(), path);
    if ((is == null)) {
      is = Resources.getResourceAsStream(classname.getClassLoader(), path);
    }
    return is;
  }
  
  /**
   * Replies the input stream of a resource.
   * <p>
   * You may use Unix-like syntax to write the resource path, ie.
   * you may use slashes to separate filenames, and may not start the
   * path with a slash.
   * <p>
   * If the <var>classLoader</var> parameter is <code>null</code>,
   * the class loader replied by {@link ClassLoaderFinder} is used.
   * If this last is <code>null</code>, the class loader of
   * the Resources class is used.
   * 
   * @param classLoader is the research scope. If <code>null</code>,
   * the class loader replied by {@link ClassLoaderFinder} is used.
   * @param path is the absolute path of the resource.
   * @return the url of the resource or <code>null</code> if the resource was
   * not found in class paths.
   */
  @Pure
  public static InputStream getResourceAsStream(final ClassLoader classLoader, final String path) {
    if ((path == null)) {
      return null;
    }
    String resourcePath = path;
    boolean _startsWith = path.startsWith("/");
    if (_startsWith) {
      resourcePath = path.substring(1);
    }
    ClassLoader loader = classLoader;
    if ((loader == null)) {
      loader = Resources.class.getClassLoader();
    }
    class $AssertEvaluator$ {
      final boolean $$result;
      $AssertEvaluator$(final ClassLoader loader) {
        this.$$result = (loader != null);
      }
    }
    assert new $AssertEvaluator$(loader).$$result;
    InputStream is = loader.getResourceAsStream(resourcePath);
    if ((is == null)) {
      is = loader.getResourceAsStream((Resources.RESOURCE_PREFIX + resourcePath));
    }
    return is;
  }
  
  /**
   * Replies the URL of a property resource that is associated to the given class.
   * 
   * @param classname is the class for which the property resource should be replied.
   * @param locale is the expected localization of the resource file; or <code>null</code>
   * for the default.
   * @return the url of the property resource or <code>null</code> if the resource was
   * not found in class paths.
   * @since 7.0
   */
  @Pure
  public static URL getPropertyFile(final Class<?> classname, final Locale locale) {
    return Resources.getPropertyFile(classname.getClassLoader(), classname, locale);
  }
  
  /**
   * Replies the URL of a property resource that is associated to the given class.
   * 
   * @param classLoader is the research scope. If <code>null</code>,
   * the class loader replied by {@link ClassLoaderFinder} is used.
   * @param classname is the class for which the property resource should be replied.
   * @param locale is the expected localization of the resource file; or <code>null</code>
   * for the default.
   * @return the url of the property resource or <code>null</code> if the resource was
   * not found in class paths.
   */
  @Pure
  public static URL getPropertyFile(final ClassLoader classLoader, final Class<?> classname, final Locale locale) {
    StringBuilder name = new StringBuilder();
    if ((locale != null)) {
      String country = locale.getCountry();
      boolean _isNullOrEmpty = StringExtensions.isNullOrEmpty(country);
      if ((!_isNullOrEmpty)) {
        name.append(classname.getSimpleName());
        name.append("_");
        name.append(country);
        name.append(".properties");
        URL url = Resources.getResource(classLoader, classname.getPackage(), name.toString());
        if ((url != null)) {
          return url;
        }
      }
    }
    name.setLength(0);
    name.append(classname.getSimpleName());
    name.append(".properties");
    return Resources.getResource(classLoader, classname.getPackage(), name.toString());
  }
  
  /**
   * Translate the given resource name according to the current JVM standard.
   * <p>
   * The <code>resourceName</code> argument should be a fully
   * qualified class name. However, for compatibility with earlier
   * versions, Sun's Java SE Runtime Environments do not verify this,
   * and so it is possible to access <code>PropertyResourceBundle</code>s
   * by specifying a path name (using "/") instead of a fully
   * qualified class name (using ".").
   * In several VM, such as Dalvik, the translation from "." to "/" is not
   * automatically done by the VM to retreive the file.
   * 
   * @param resourceName
   * @return the translated resource name.
   * @since 7.0
   */
  @Inline("($1)")
  @Pure
  public static String translateResourceName(final String resourceName) {
    return resourceName;
  }
  
  @SyntheticMember
  public Resources() {
    super();
  }
}
