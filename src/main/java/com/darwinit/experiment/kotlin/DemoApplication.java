package com.darwinit.experiment.kotlin;

import org.jetbrains.kotlin.script.jsr223.KotlinJsr223JvmLocalScriptEngineFactory;

import javax.script.*;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;

public class DemoApplication {

    public static void main(String[] args) {
        long time = System.currentTimeMillis();
        ScriptEngine engine = new KotlinJsr223JvmLocalScriptEngineFactory().getScriptEngine();
        System.out.println("in "+(System.currentTimeMillis()-time)+" seconds");

        Bindings bindings = engine.getBindings(ScriptContext.ENGINE_SCOPE);
        bindings.put("name", "christof");

        time=System.currentTimeMillis();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(DemoApplication.class.getClassLoader().getResource("./scripts/hello.kts").getFile())));

            if(!(engine instanceof Compilable)) {
                System.out.println("engine should be a compilable");
                return;
            }

            if(!(engine instanceof Invocable)) {
                System.out.println("engine should be a invocable");
                return;
            }

            CompiledScript compiledScript = ((Compilable)engine).compile(reader);
            System.out.println("compilation in "+(System.currentTimeMillis()-time)+" seconds");
            time=System.currentTimeMillis();

            // for use of bindings
            compiledScript.eval(bindings);
            System.out.println("evaluation in "+(System.currentTimeMillis()-time)+" seconds");
            time=System.currentTimeMillis();

            Invocable invocableEngine=(Invocable) engine;
            Object result = invocableEngine.invokeFunction("Hello", "christof");
            System.out.println("result: "+result);
            System.out.println("invoke method 1 in "+(System.currentTimeMillis()-time)+" seconds");
            time=System.currentTimeMillis();

            result = invocableEngine.invokeFunction("Hello", "mélisande");
            System.out.println("result: "+result);
            System.out.println("invoke method 2 in "+(System.currentTimeMillis()-time)+" seconds");
            time=System.currentTimeMillis();

            result = invocableEngine.invokeFunction("Hello", "malorie");
            System.out.println("result: "+result);
            System.out.println("invoke method 3 in "+(System.currentTimeMillis()-time)+" seconds");
            time=System.currentTimeMillis();

            result = invocableEngine.invokeFunction("View", new Application());
            System.out.println("invoke method 4 in "+(System.currentTimeMillis()-time)+" seconds");
            time=System.currentTimeMillis();
        } catch (ScriptException e1) {
            e1.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        time = System.currentTimeMillis() - time;
        System.out.println("in "+time+" seconds");
    }
}
