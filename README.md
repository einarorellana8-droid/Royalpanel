# ROYAL PANEL Android — V0.1

Proyecto inicial Android (Kotlin) para Android 13+.

## Qué hace esta versión
- Muestra la interfaz inicial de ROYAL PANEL.
- Solicita al usuario permiso explícito de captura de pantalla mediante Android MediaProjection.
- Inicia un servicio en primer plano con notificación persistente.
- Permite detener el servicio desde la aplicación.

## Qué NO hace todavía
- No identifica automáticamente cartas.
- No lee el elixir real del rival.
- No interactúa con Clash Royale ni modifica el juego.
- La captura todavía no se procesa ni se guarda. Esta versión valida el permiso y el servicio.

## Compilar el APK desde GitHub
1. Sube el contenido de este proyecto a un repositorio GitHub llamado `royal-panel`.
2. Abre la pestaña **Actions**.
3. Selecciona **Build ROYAL PANEL APK** y pulsa **Run workflow** (o espera a que compile tras subir archivos).
4. Abre la ejecución completada y descarga el artefacto `ROYAL-PANEL-APK`.
5. Extrae el APK del ZIP del artefacto e instálalo en Android.

La compilación usa GitHub Actions y JDK 17. El APK de depuración no está firmado para publicación en Play Store.
