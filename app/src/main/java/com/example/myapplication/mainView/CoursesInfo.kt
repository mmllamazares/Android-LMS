package com.example.myapplication.mainView

import androidx.compose.ui.graphics.Color

data class CourseItem(
    val id: Int,
    val title: String,
    val description: String,
    val content: String,
    val progress: Float?,        // null = no iniciado
    val totalLessons: Int?,
    val completedLessons: Int?,
    val duration: String?,
    val modules: Int?,
    val students: Int?,
    val avatarColors: List<Color>?,
    val buttonLabel: String,
    val imageColor: Color
)

object Courses {

    val dummyCourses = listOf(
        CourseItem(
            id = 0,
            title = "Acceso Cameral",
            description = "Introducción al procedimiento quirúrgico de apertura de la cámara pulpar y su " +
                    "propósito clínico.",
            content = " Fase quirúrgica con la que se abre la cámara pulpar previa a la localización y " +
                    "exploración de los conductos. Su objetivo es obtener un acceso directo, amplio y sin " +
                    "obstáculos a la zona apical del conducto radicular, para lo cual es indispensable dominar la " +
                    "morfología de la cámara pulpar, así como las posibles alteraciones que puedan presentarse",
            progress = null,
            totalLessons = null,
            completedLessons = null,
            duration = null,
            modules = null,
            students = null,
            avatarColors = null,
            buttonLabel = "Ver detalles",
            imageColor = Color(0xFF1E3A5F)
        ),
        CourseItem(
            id = 1,
            title = "Aspectos a considerar",
            description = "Principios y reglas fundamentales que guían la correcta ejecución del acceso " +
                    "cameral.",
            content = """1. La forma de la cavidad de acceso cameral debe corresponder a la forma de la cámara.
2. Abordaje: dientes anteriores por la cara lingual o palatina; dientes posteriores por la cara oclusal.
3. En los dientes multiradiculares, el acceso debe incluir el techo de la cámara pulpar completo y debe evitarse la deformación del piso.
4. No debe localizarse el conducto a través de la cavidad de caries.
5. La pulpa cameral es mayor en un diente joven que en uno adulto, y el acceso cameral debe ser igual.
6. El límite de la apertura coronaria deberá incluir todos los cuernos pulpares.
7. Nunca deberá deformarse la pared cervical o el piso de la cámara pulpar.""",
            progress = null,
            totalLessons = null,
            completedLessons = null,
            duration = null,
            modules = null,
            students = null,
            avatarColors = null,
            buttonLabel = "Ver Detalles",
            imageColor = Color(0xFF2D6A4F)
        ),
        CourseItem(
            id = 2,
            title = "Pasos de la técnica",
            description = " Secuencia clínica paso a paso para realizar la apertura de la cámara pulpar.",
            content = """1. Apertura de la cámara pulpar.
2. Remoción del techo de la cámara.
3. Eliminación de la pulpa cameral.
4. Rectificación de las paredes de la cámara para tener acceso amplio y directo a la zona apical del conducto. Se realiza el desgaste compensatorio. """,
            progress = null,
            totalLessons = null,
            completedLessons = null,
            duration = null,
            modules = null,
            students = null,
            avatarColors = null,
            buttonLabel = "Ver detalles",
            imageColor = Color(0xFF1E3A5F)
        ),
        CourseItem(
            id = 3,
            title = "Dientes Anteriores (Incisivos y Caninos)",
            description = "Técnica específica de acceso cameral para incisivos y caninos, incluyendo " +
                    "fresas y puntos de referencia",
            content = """ La entrada a la cavidad endodóntica siempre se realiza por la cara palatina o lingual en todos los dientes anteriores. La penetración inicial se hace en el tercio medio-medio, en el centro exacto de la superficie lingual, en la posición de 2 a 3 mm del borde incisal y de 1 a 2 mm del cingulum.
    
    La entrada inicial se prepara con fresa de fisura troncocónica 701 o fisura cilíndrica 556 con superalta velocidad, en posición perpendicular al eje longitudinal del diente. Se penetra solo en esmalte hasta el límite amelodentinario; después del punto de penetración, se gira la mano hacia incisal para que la fresa tome la dirección paralela al eje longitudinal del diente, hasta sentir la sensación de caída, descenso o vacío, lo que indica que se está en la cámara pulpar. Se extiende con movimientos de vaivén de mesial a distal; posteriormente se trabaja con micromotor dentro de la cámara con fresa redonda #4 para eliminar las paredes labiales y linguales del techo de la cámara.
    
    Ocasionalmente deben utilizarse fresas 1 o 2 redondas para eliminar los escombros de los cuernos pulpares y las bacterias; esto también previene el descoloramiento futuro por restos de tejido pulpar.
    
    En incisivos la forma es triangular; en el canino es romboidal u ovoide (los movimientos son más pronunciados inciso-cervical que mesiodistal). En el diente joven con pulpa grande, el contorno refleja la anatomía interior triangular; en el diente adulto con la cámara obliterada con dentina secundaria, es ovoide.
""".trimIndent(),
            progress = null,
            totalLessons = null,
            completedLessons = null,
            duration = null,
            modules = null,
            students = null,
            avatarColors = null,
            buttonLabel = "Ver detalles",
            imageColor = Color(0xFF1E3A5F)
        ),
        CourseItem(
            id = 4,
            title = "Premolares Superiores e Inferiores",
            description = "Técnica de apertura cameral para premolares con sus particularidades anatómicas.",
            content = """ La entrada siempre se realiza a través de la superficie oclusal de todos los dientes posteriores. La penetración inicial se realiza con fresa de fisura troncocónica 701 o fisura cilíndrica 556 con superalta velocidad, en el centro exacto del surco central del premolar siguiendo el eje longitudinal del diente. La fresa produce la sensación de vacío cuando llega a la cámara; luego se dirige buco-lingualmente con movimientos más intensos y proximales leves dentro de la cámara; a continuación, con fresa redonda #4 se eliminan las paredes labiales y linguales del techo de la cámara.
    
    La preparación tiene contorno ovoide. Por las características anatómicas de los premolares inferiores, cuya cara lingual mira hacia la lengua, es necesario incluir la cúspide vestibular en la apertura cameral.
""".trimIndent(),
            progress = null,
            totalLessons = null,
            completedLessons = null,
            duration = null,
            modules = null,
            students = null,
            avatarColors = null,
            buttonLabel = "Ver detalles",
            imageColor = Color(0xFF1E3A5F)
        ),
        CourseItem(
            id = 5,
            title = "Molares Maxilares",
            description = "Técnica y orientación de la apertura cameral en molares superiores con su forma triangular o trapezoidal.",
            content = """La entrada se realiza por la superficie oclusal en los 2/3 mesiales; el contorno es triangular con base hacia vestibular y vértice hacia palatino. La penetración inicial se realiza con fresa de fisura troncocónica 701 o fisura cilíndrica 556 con superalta velocidad.
    
    La fresa debe dirigirse hacia el cuerno mesio-bucal, que se encuentra cerca de la cúspide homónima (MV); se sentirá la sensación de vacío. Dentro de la cámara se cambia la posición de la fresa en dirección disto-bucal, un poco allá del surco bucal, sin cruzar el reborde oblicuo, antes de la cúspide y no debajo de ella. Después se cambia la dirección hacia palatino y se encuentra el cuerno pulpar del conducto palatino, aproximadamente debajo de la cúspide mesio-palatina. Se vuelve a mesio-bucal y se cierra el triángulo; luego con fresa redonda #4 se eliminan los restos del techo de la cámara.
    
    En el primer molar superior se modifica el contorno a una forma trapezoidal, debido a que el conducto distobucal no está tan próximo a la superficie bucal como el mesial.
""".trimIndent(),
            progress = null,
            totalLessons = null,
            completedLessons = null,
            duration = null,
            modules = null,
            students = null,
            avatarColors = null,
            buttonLabel = "Ver detalles",
            imageColor = Color(0xFF1E3A5F)
        ),
        CourseItem(
            id = 6,
            title = "Molares Mandibulares",
            description = "Técnica de apertura cameral para molares inferiores, con forma triangular de base mesial y vértice distal.",
            content = """La entrada se realiza por la superficie oclusal. La penetración inicial se realiza con fresa de fisura troncocónica 701 o fisura cilíndrica 556 con superalta velocidad, en un punto que está entre el vértice de la cúspide mesio-vestibular y la estría central, siguiendo el eje longitudinal del diente. Se sentirá la sensación de vacío; ahí se encuentra el cuerno mesio-bucal. Entonces se extiende la fresa 2 mm hacia lingual a nivel del surco central y se localiza el cuerno mesio-lingual; se cambia la dirección hacia distal, hacia la fosa central, y se encuentra el cuerno pulpar distal. Se cierra el triángulo y luego con fresa redonda #4 se eliminan los restos del techo de la cámara.
    
    El desgaste compensatorio se realiza con fresas. El triángulo presenta base hacia mesial y vértice a distal. En ocasiones, por la presencia de un segundo conducto distal en el primer molar, adquiere forma rectangular. El acceso cameral se encuentra en la mitad mesial.
""".trimIndent(),
            progress = null,
            totalLessons = null,
            completedLessons = null,
            duration = null,
            modules = null,
            students = null,
            avatarColors = null,
            buttonLabel = "Ver detalles",
            imageColor = Color(0xFF1E3A5F)
        ),
        CourseItem(
            id = 7,
            title = "Instrumentación de los Conductos Radiculares",
            description = "Objetivo y fundamento de la instrumentación radicular dentro de la tríada endodóntica.",
            content = """La instrumentación de los conductos radiculares busca limpiar los conductos de restos de tejido pulpar, bacterias y restos tisulares necróticos, y darles una forma que permita su relleno con un material biológicamente inerte.
    
    La instrumentación debe llegar hasta la parte más estrecha del conducto: la constricción apical. La mayoría de las veces esta constricción coincide con el límite cemento-dentinario.
    
    Tríada Endodóntica (piedra angular del éxito del tratamiento):
    * Asepsia
    * Preparación Biomecánica
    * Sellado Hermético
""".trimIndent(),
            progress = null,
            totalLessons = null,
            completedLessons = null,
            duration = null,
            modules = null,
            students = null,
            avatarColors = null,
            buttonLabel = "Ver detalles",
            imageColor = Color(0xFF1E3A5F)
        )
//        CourseItem(
//            id = 0,
//            title = "title",
//            description = "Introducción al procedimiento quirúrgico de apertura de la cámara pulpar y su " +
//                    "propósito clínico.",
//            content = """ """,
//            progress = null,
//            totalLessons = null,
//            completedLessons = null,
//            duration = null,
//            modules = null,
//            students = null,
//            avatarColors = null,
//            buttonLabel = "Ver detalles",
//            imageColor = Color(0xFF1E3A5F)
//        )
//        CourseItem(
//            title = "Instrumental Quirúrgico",
//            description = "Reconocimiento y uso de instrumental rotatorio y manual.",
//            progress = null,
//            totalLessons = null,
//            completedLessons = null,
//            duration = null,
//            modules = null,
//            students = 120,
//            avatarColors = listOf(Color(0xFF6366F1), Color(0xFF10B981), Color(0xFFF59E0B)),
//            buttonLabel = "Empezar Curso",
//            imageColor = Color(0xFF1F4068)
//        )
    )
}