package com.facebook.react.bridge;

import com.citrix.loggersdk.BuildConfig;
import com.facebook.react.bridge.NativeModule;
import com.microsoft.identity.common.internal.cache.CacheKeyValueDelegate;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* compiled from: PG */
public class JavaMethodWrapper implements NativeModule.NativeMethod {
    public static final ArgumentExtractor<ReadableArray> ARGUMENT_EXTRACTOR_ARRAY = new ArgumentExtractor<ReadableArray>() {
        public ReadableArray extractArgument(JSInstance jSInstance, ReadableArray readableArray, int i) {
            return readableArray.getArray(i);
        }
    };
    public static final ArgumentExtractor<Boolean> ARGUMENT_EXTRACTOR_BOOLEAN = new ArgumentExtractor<Boolean>() {
        public Boolean extractArgument(JSInstance jSInstance, ReadableArray readableArray, int i) {
            return Boolean.valueOf(readableArray.getBoolean(i));
        }
    };
    public static final ArgumentExtractor<Callback> ARGUMENT_EXTRACTOR_CALLBACK = new ArgumentExtractor<Callback>() {
        public Callback extractArgument(JSInstance jSInstance, ReadableArray readableArray, int i) {
            if (readableArray.isNull(i)) {
                return null;
            }
            return new CallbackImpl(jSInstance, (int) readableArray.getDouble(i));
        }
    };
    public static final ArgumentExtractor<Double> ARGUMENT_EXTRACTOR_DOUBLE = new ArgumentExtractor<Double>() {
        public Double extractArgument(JSInstance jSInstance, ReadableArray readableArray, int i) {
            return Double.valueOf(readableArray.getDouble(i));
        }
    };
    public static final ArgumentExtractor<Dynamic> ARGUMENT_EXTRACTOR_DYNAMIC = new ArgumentExtractor<Dynamic>() {
        public Dynamic extractArgument(JSInstance jSInstance, ReadableArray readableArray, int i) {
            return DynamicFromArray.create(readableArray, i);
        }
    };
    public static final ArgumentExtractor<Float> ARGUMENT_EXTRACTOR_FLOAT = new ArgumentExtractor<Float>() {
        public Float extractArgument(JSInstance jSInstance, ReadableArray readableArray, int i) {
            return Float.valueOf((float) readableArray.getDouble(i));
        }
    };
    public static final ArgumentExtractor<Integer> ARGUMENT_EXTRACTOR_INTEGER = new ArgumentExtractor<Integer>() {
        public Integer extractArgument(JSInstance jSInstance, ReadableArray readableArray, int i) {
            return Integer.valueOf((int) readableArray.getDouble(i));
        }
    };
    public static final ArgumentExtractor<ReadableMap> ARGUMENT_EXTRACTOR_MAP = new ArgumentExtractor<ReadableMap>() {
        public ReadableMap extractArgument(JSInstance jSInstance, ReadableArray readableArray, int i) {
            return readableArray.getMap(i);
        }
    };
    public static final ArgumentExtractor<Promise> ARGUMENT_EXTRACTOR_PROMISE = new ArgumentExtractor<Promise>() {
        public int getJSArgumentsNeeded() {
            return 2;
        }

        public Promise extractArgument(JSInstance jSInstance, ReadableArray readableArray, int i) {
            return new PromiseImpl((Callback) JavaMethodWrapper.ARGUMENT_EXTRACTOR_CALLBACK.extractArgument(jSInstance, readableArray, i), (Callback) JavaMethodWrapper.ARGUMENT_EXTRACTOR_CALLBACK.extractArgument(jSInstance, readableArray, i + 1));
        }
    };
    public static final ArgumentExtractor<String> ARGUMENT_EXTRACTOR_STRING = new ArgumentExtractor<String>() {
        public String extractArgument(JSInstance jSInstance, ReadableArray readableArray, int i) {
            return readableArray.getString(i);
        }
    };
    public static final boolean DEBUG = false;
    public ArgumentExtractor[] mArgumentExtractors;
    public Object[] mArguments;
    public boolean mArgumentsProcessed = false;
    public int mJSArgumentsNeeded;
    public final Method mMethod;
    public final JavaModuleWrapper mModuleWrapper;
    public final int mParamLength;
    public final Class[] mParameterTypes;
    public String mSignature;
    public String mType = BaseJavaModule.METHOD_TYPE_ASYNC;

    /* compiled from: PG */
    public static abstract class ArgumentExtractor<T> {
        public ArgumentExtractor() {
        }

        public abstract T extractArgument(JSInstance jSInstance, ReadableArray readableArray, int i);

        public int getJSArgumentsNeeded() {
            return 1;
        }
    }

    static {
        fr.a.a(gr.b);
    }

    public JavaMethodWrapper(JavaModuleWrapper javaModuleWrapper, Method method, boolean z) {
        this.mModuleWrapper = javaModuleWrapper;
        this.mMethod = method;
        this.mMethod.setAccessible(true);
        this.mParameterTypes = this.mMethod.getParameterTypes();
        Class<Promise>[] clsArr = this.mParameterTypes;
        this.mParamLength = clsArr.length;
        if (z) {
            this.mType = BaseJavaModule.METHOD_TYPE_SYNC;
            return;
        }
        int i = this.mParamLength;
        if (i > 0 && clsArr[i - 1] == Promise.class) {
            this.mType = BaseJavaModule.METHOD_TYPE_PROMISE;
        }
    }

    private ArgumentExtractor[] buildArgumentExtractors(Class[] clsArr) {
        ArgumentExtractor[] argumentExtractorArr = new ArgumentExtractor[clsArr.length];
        for (int i = 0; i < clsArr.length; i += argumentExtractorArr[i].getJSArgumentsNeeded()) {
            Class<Dynamic> cls = clsArr[i];
            if (cls == Boolean.class || cls == Boolean.TYPE) {
                argumentExtractorArr[i] = ARGUMENT_EXTRACTOR_BOOLEAN;
            } else if (cls == Integer.class || cls == Integer.TYPE) {
                argumentExtractorArr[i] = ARGUMENT_EXTRACTOR_INTEGER;
            } else if (cls == Double.class || cls == Double.TYPE) {
                argumentExtractorArr[i] = ARGUMENT_EXTRACTOR_DOUBLE;
            } else if (cls == Float.class || cls == Float.TYPE) {
                argumentExtractorArr[i] = ARGUMENT_EXTRACTOR_FLOAT;
            } else if (cls == String.class) {
                argumentExtractorArr[i] = ARGUMENT_EXTRACTOR_STRING;
            } else if (cls == Callback.class) {
                argumentExtractorArr[i] = ARGUMENT_EXTRACTOR_CALLBACK;
            } else if (cls == Promise.class) {
                argumentExtractorArr[i] = ARGUMENT_EXTRACTOR_PROMISE;
                boolean z = true;
                if (i != clsArr.length - 1) {
                    z = false;
                }
                Kw.a(z, "Promise must be used as last parameter only");
            } else if (cls == ReadableMap.class) {
                argumentExtractorArr[i] = ARGUMENT_EXTRACTOR_MAP;
            } else if (cls == ReadableArray.class) {
                argumentExtractorArr[i] = ARGUMENT_EXTRACTOR_ARRAY;
            } else if (cls == Dynamic.class) {
                argumentExtractorArr[i] = ARGUMENT_EXTRACTOR_DYNAMIC;
            } else {
                StringBuilder a = Eo.a("Got unknown argument class: ");
                a.append(cls.getSimpleName());
                throw new RuntimeException(a.toString());
            }
        }
        return argumentExtractorArr;
    }

    private String buildSignature(Method method, Class[] clsArr, boolean z) {
        StringBuilder sb = new StringBuilder(clsArr.length + 2);
        if (z) {
            sb.append(returnTypeToChar(method.getReturnType()));
            sb.append('.');
        } else {
            sb.append("v.");
        }
        for (int i = 0; i < clsArr.length; i++) {
            Class<Promise> cls = clsArr[i];
            if (cls == Promise.class) {
                boolean z2 = true;
                if (i != clsArr.length - 1) {
                    z2 = false;
                }
                Kw.a(z2, "Promise must be used as last parameter only");
            }
            sb.append(paramTypeToChar(cls));
        }
        return sb.toString();
    }

    private int calculateJSArgumentsNeeded() {
        ArgumentExtractor[] argumentExtractorArr = this.mArgumentExtractors;
        Kw.a(argumentExtractorArr);
        int i = 0;
        for (ArgumentExtractor jSArgumentsNeeded : argumentExtractorArr) {
            i += jSArgumentsNeeded.getJSArgumentsNeeded();
        }
        return i;
    }

    public static char commonTypeToChar(Class cls) {
        if (cls == Boolean.TYPE) {
            return 'z';
        }
        if (cls == Boolean.class) {
            return 'Z';
        }
        if (cls == Integer.TYPE) {
            return 'i';
        }
        if (cls == Integer.class) {
            return 'I';
        }
        if (cls == Double.TYPE) {
            return 'd';
        }
        if (cls == Double.class) {
            return 'D';
        }
        if (cls == Float.TYPE) {
            return 'f';
        }
        if (cls == Float.class) {
            return 'F';
        }
        return cls == String.class ? 'S' : 0;
    }

    private String getAffectedRange(int i, int i2) {
        if (i2 <= 1) {
            return Eo.b(BuildConfig.FLAVOR, i);
        }
        StringBuilder b = Eo.b(BuildConfig.FLAVOR, i, CacheKeyValueDelegate.CACHE_VALUE_SEPARATOR);
        b.append((i + i2) - 1);
        return b.toString();
    }

    public static char paramTypeToChar(Class cls) {
        char commonTypeToChar = commonTypeToChar(cls);
        if (commonTypeToChar != 0) {
            return commonTypeToChar;
        }
        if (cls == Callback.class) {
            return 'X';
        }
        if (cls == Promise.class) {
            return 'P';
        }
        if (cls == ReadableMap.class) {
            return 'M';
        }
        if (cls == ReadableArray.class) {
            return 'A';
        }
        if (cls == Dynamic.class) {
            return 'Y';
        }
        StringBuilder a = Eo.a("Got unknown param class: ");
        a.append(cls.getSimpleName());
        throw new RuntimeException(a.toString());
    }

    private void processArguments() {
        if (!this.mArgumentsProcessed) {
            dF dFVar = cF.a;
            dFVar.a("method", this.mModuleWrapper.getName() + "." + this.mMethod.getName());
            dF dFVar2 = dFVar;
            try {
                this.mArgumentsProcessed = true;
                this.mArgumentExtractors = buildArgumentExtractors(this.mParameterTypes);
                this.mSignature = buildSignature(this.mMethod, this.mParameterTypes, this.mType.equals(BaseJavaModule.METHOD_TYPE_SYNC));
                this.mArguments = new Object[this.mParameterTypes.length];
                this.mJSArgumentsNeeded = calculateJSArgumentsNeeded();
            } finally {
                cF.a.a();
            }
        }
    }

    public static char returnTypeToChar(Class cls) {
        char commonTypeToChar = commonTypeToChar(cls);
        if (commonTypeToChar != 0) {
            return commonTypeToChar;
        }
        if (cls == Void.TYPE) {
            return 'v';
        }
        if (cls == WritableMap.class) {
            return 'M';
        }
        if (cls == WritableArray.class) {
            return 'A';
        }
        StringBuilder a = Eo.a("Got unknown return class: ");
        a.append(cls.getSimpleName());
        throw new RuntimeException(a.toString());
    }

    public Method getMethod() {
        return this.mMethod;
    }

    public String getSignature() {
        if (!this.mArgumentsProcessed) {
            processArguments();
        }
        String str = this.mSignature;
        Kw.a(str);
        return str;
    }

    public String getType() {
        return this.mType;
    }

    public void invoke(JSInstance jSInstance, ReadableArray readableArray) {
        int i;
        String str = this.mModuleWrapper.getName() + "." + this.mMethod.getName();
        dF dFVar = cF.a;
        dFVar.a("method", str);
        dF dFVar2 = dFVar;
        int i2 = 0;
        if (DEBUG) {
            fr.a.a(gr.b, "JS->Java: %s.%s()", new Object[]{this.mModuleWrapper.getName(), this.mMethod.getName()});
        }
        try {
            if (!this.mArgumentsProcessed) {
                processArguments();
            }
            if (this.mArguments == null || this.mArgumentExtractors == null) {
                throw new Error("processArguments failed");
            } else if (this.mJSArgumentsNeeded == readableArray.size()) {
                i = 0;
                while (i2 < this.mArgumentExtractors.length) {
                    this.mArguments[i2] = this.mArgumentExtractors[i2].extractArgument(jSInstance, readableArray, i);
                    i += this.mArgumentExtractors[i2].getJSArgumentsNeeded();
                    i2++;
                }
                this.mMethod.invoke(this.mModuleWrapper.getModule(), this.mArguments);
                cF.a.a();
            } else {
                throw new NativeArgumentsParseException(str + " got " + readableArray.size() + " arguments, expected " + this.mJSArgumentsNeeded);
            }
        } catch (UnexpectedNativeTypeException e) {
            throw new NativeArgumentsParseException(e.getMessage() + " (constructing arguments for " + str + " at argument index " + getAffectedRange(i, this.mArgumentExtractors[i2].getJSArgumentsNeeded()) + ")", e);
        } catch (IllegalArgumentException e2) {
            throw new RuntimeException("Could not invoke " + str, e2);
        } catch (IllegalAccessException e3) {
            throw new RuntimeException("Could not invoke " + str, e3);
        } catch (InvocationTargetException e4) {
            if (e4.getCause() instanceof RuntimeException) {
                throw ((RuntimeException) e4.getCause());
            }
            throw new RuntimeException("Could not invoke " + str, e4);
        } catch (Throwable th) {
            cF.a.a();
            throw th;
        }
    }
}
