package org.ifinalframework.api.idea.action;

import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiMethod
import com.itangcent.idea.plugin.actions.BasicAnAction
import com.itangcent.idea.plugin.api.cache.DefaultFileApiCacheRepository
import com.itangcent.idea.plugin.api.cache.FileApiCacheRepository
import com.itangcent.idea.plugin.api.cache.ProjectCacheRepository
import com.itangcent.idea.plugin.api.export.ExportChannel
import com.itangcent.idea.plugin.api.export.ExportDoc
import com.itangcent.idea.plugin.api.export.core.*
import com.itangcent.idea.plugin.api.export.spring.SpringRequestClassExporter
import com.itangcent.idea.plugin.api.export.yapi.*
import com.itangcent.idea.plugin.config.EnhancedConfigReader
import com.itangcent.idea.plugin.rule.SuvRuleParser
import com.itangcent.idea.plugin.settings.helper.YapiTokenChecker
import com.itangcent.idea.utils.CustomizedPsiClassHelper
import com.itangcent.idea.utils.RuleComputeListenerRegistry
import com.itangcent.intellij.config.ConfigReader
import com.itangcent.intellij.config.rule.RuleComputeListener
import com.itangcent.intellij.config.rule.RuleParser
import com.itangcent.intellij.context.ActionContext
import com.itangcent.intellij.extend.guice.singleton
import com.itangcent.intellij.extend.guice.with
import com.itangcent.intellij.file.DefaultLocalFileRepository
import com.itangcent.intellij.file.LocalFileRepository
import com.itangcent.intellij.jvm.PsiClassHelper
import org.ifinalframework.api.idea.yapi.YapiOpener
import javax.swing.Icon


/**
 * 打开API文档
 *
 * @author iimik
 * @since 0.0.1
 **/
class OpenApiAction : BasicAnAction {
    constructor() : super()
    constructor(icon: Icon?) : super(icon)
    constructor(text: String?) : super(text)
    constructor(text: String?, description: String?, icon: Icon?) : super(text, description, icon)

    override fun afterBuildActionContext(event: AnActionEvent, builder: ActionContext.ActionContextBuilder) {
        super.afterBuildActionContext(event, builder)


        builder.bind(RuleParser::class) { it.with(SuvRuleParser::class).singleton() }
        builder.bind(RuleComputeListener::class) { it.with(RuleComputeListenerRegistry::class).singleton() }
        builder.bind(PsiClassHelper::class) { it.with(CustomizedPsiClassHelper::class).singleton() }

        builder.bind(ClassExporter::class) { it.with(SpringRequestClassExporter::class).singleton() }

        builder.bind(FileApiCacheRepository::class) { it.with(DefaultFileApiCacheRepository::class).singleton() }
        builder.bind(LocalFileRepository::class, "projectCacheRepository") {
            it.with(ProjectCacheRepository::class).singleton()
        }

        builder.bind(ConfigReader::class) { it.with(EnhancedConfigReader::class).singleton() }

        builder.bind(LocalFileRepository::class) { it.with(DefaultLocalFileRepository::class).singleton() }

        builder.bind(LinkResolver::class) { it.with(YapiLinkResolver::class).singleton() }

        builder.bind(YapiApiHelper::class) { it.with(CachedYapiApiHelper::class).singleton() }

        builder.bind(ClassExporter::class) { it.with(CompositeClassExporter::class).singleton() }

        builder.bindInstance(ExportChannel::class, ExportChannel.of("yapi"))
        builder.bindInstance(ExportDoc::class, ExportDoc.of("request", "methodDoc"))

        builder.bind(RequestBuilderListener::class) { it.with(CompositeRequestBuilderListener::class).singleton() }
        builder.bind(MethodDocBuilderListener::class) { it.with(CompositeMethodDocBuilderListener::class).singleton() }

        builder.bind(MethodFilter::class) { it.with(ConfigurableMethodFilter::class).singleton() }

        builder.bindInstance("file.save.default", "yapi.json")
        builder.bindInstance("file.save.last.location.key", "com.itangcent.yapi.export.path")

        builder.bind(PsiClassHelper::class) { it.with(YapiPsiClassHelper::class).singleton() }

        builder.bind(YapiTokenChecker::class) { it.with(YapiTokenCheckerSupport::class).singleton() }

        builder.bind(AdditionalParseHelper::class) { it.with(YapiAdditionalParseHelper::class).singleton() }
    }

    override fun actionPerformed(actionContext: ActionContext, project: Project?, anActionEvent: AnActionEvent) {
        val opener = actionContext.instance(YapiOpener::class)
        val psiElement = actionContext.callInReadUI { anActionEvent.getData(CommonDataKeys.PSI_ELEMENT) }
        if (psiElement is PsiMethod) {
            opener.open(psiElement)
        }
    }
}